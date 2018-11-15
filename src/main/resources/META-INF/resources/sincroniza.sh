find . -name target -type d -exec rm -rf {} \;

git pull origin master
git add --all
git commit -m "Atualizacao automática"
git push origin master
 
