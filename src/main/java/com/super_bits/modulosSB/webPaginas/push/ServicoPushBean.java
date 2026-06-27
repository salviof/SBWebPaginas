package com.super_bits.modulosSB.webPaginas.push;

import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.webPaginas.JSFManagedBeans.declarados.util.ItfServicoPush;
import jakarta.json.JsonObjectBuilder;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import java.util.Map;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 * CarameloPush — Serviço de Push Server Side
 *
 * Bean CDI ApplicationScoped que serve como ponte entre o domínio da aplicação
 * e o PushEndpointNativo (JSR-356).
 *
 * Implementa ItfServicoPush para que os módulos da biblioteca (SBWebPaginas,
 * intERPRestful, etc) não precisem conhecer o OmniFaces ou qualquer
 * implementação de push específica.
 *
 * Compatível com: - Java 8 - Weld 2.4.8 (CDI javax.enterprise.context) -
 * javax.json (já presente no pom.xml do projeto)
 */
@Named
@ApplicationScoped
public class ServicoPushBean implements ItfServicoPush {

    private static final Logger LOG = Logger.getLogger(ServicoPushBean.class.getName());

    // ─── API pública ──────────────────────────────────────────────────────────
    /**
     * Envia uma mensagem push para o usuário identificado pelo email. Entrega
     * para todas as abas abertas do usuário simultaneamente.
     *
     * @param emailDestinatario Email do usuário destinatário
     * @param mensagem Map com os dados da mensagem (será serializado em JSON)
     * @return true se entregou para pelo menos uma aba, false se usuário
     * offline
     */
    @Override
    public boolean enviar(String emailDestinatario, Map<String, Object> mensagem) {
        if (emailDestinatario == null || emailDestinatario.trim().isEmpty()) {
            LOG.warning("[ServicoPush] Email destinatário não informado.");
            return false;
        }

        if (mensagem == null || mensagem.isEmpty()) {
            LOG.warning("[ServicoPush] Mensagem vazia para: " + emailDestinatario);
            return false;
        }

        String json = _toJson(mensagem);
        LOG.fine("[ServicoPush] Enviando para " + emailDestinatario + ": " + json);

        return PushEndpointNativo.enviar(emailDestinatario, json);
    }

    /**
     * Verifica se o usuário está online (tem pelo menos uma aba aberta).
     *
     * @param emailUsuario Email do usuário
     * @return true se online
     */
    @Override
    public boolean isOnline(String emailUsuario) {
        return PushEndpointNativo.isOnline(emailUsuario);
    }

    /**
     * Retorna o número de abas abertas do usuário.
     *
     * @param emailUsuario Email do usuário
     * @return quantidade de abas, 0 se offline
     */
    @Override
    public int contarAbas(String emailUsuario) {
        return PushEndpointNativo.contarAbas(emailUsuario);
    }

    // ─── Serialização JSON ────────────────────────────────────────────────────
    /**
     * Serializa o Map para JSON usando javax.json (já no classpath). Suporta
     * valores String, Number e Boolean.
     */
    private String _toJson(Map<String, Object> mensagem) {
        JsonObjectBuilder builder = null;
        try {
            builder = UtilCRCJson.getJsonBuilderBySequenciaChaveValor("versaoCRCNTF", "1");
        } catch (ErroProcessandoJson ex) {
            CarameloCode.RelatarErro(FabErro.SOLICITAR_REPARO, "Flha iniciando JsonBuilder", ex);
        }

        for (Map.Entry<String, Object> entry : mensagem.entrySet()) {
            String chave = entry.getKey();
            Object valor = entry.getValue();

            if (valor instanceof String) {
                builder.add(chave, (String) valor);
            } else if (valor instanceof Integer) {
                builder.add(chave, (Integer) valor);
            } else if (valor instanceof Long) {
                builder.add(chave, (Long) valor);
            } else if (valor instanceof Double) {
                builder.add(chave, (Double) valor);
            } else if (valor instanceof Boolean) {
                builder.add(chave, (Boolean) valor);
            } else if (valor != null) {
                builder.add(chave, valor.toString());
            }
        }

        return builder.build().toString();
    }
}
