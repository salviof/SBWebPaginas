
// Map [Enter] key to work like the [Tab] key
// Daniel P. Clark 2014

// Catch the keydown for the entire document
$(document).keydown(function (e) {
    try {
        // Set self as the current item in focus
        var self = $(':focus'),
                // Set the form by the current item in focus
                form = self.parents('form:eq(0)'),
                focusable;

        // Array of Indexable/Tab-able items
        focusable = form.find('input,a,select,button,textarea,div[contenteditable=true]').filter(':visible');

        function enterKey() {
            if (e.which === 13 && !self.is('textarea,div[contenteditable=true]')) { // [Enter] key


                if (!self.is('button[type="submit"]')) {
                    if (!self.is('button', 'a')) {


                        // If not a regular hyperlink/button/textarea
                        if ($.inArray(self, focusable)) {
                            // Then prevent the default [Enter] key behaviour from submitting the form
                            try {
                                e.preventDefault();
                            } catch (t) {

                            }
                        } // Otherwise follow the link/button as by design, or put new line in textarea
                    }

                    // Focus on the next item (either previous or next depending on shift)
                    focusable.eq(focusable.index(self) + (e.shiftKey ? -1 : 1)).focus();
                }
                return false;


            }
        }
        // We need to capture the [Shift] key and check the [Enter] key either way.
        if (e.shiftKey) {
            enterKey();
        } else {
            enterKey();
        }
    } catch (t) {

    }
});
$(function () {
    var focusedElement;
    $(document).on('focus', 'input', function () {
        if (focusedElement == this)
            return; //already focused, return so user can now place cursor at specific point in input.
        focusedElement = this;
        setTimeout(function () {
            focusedElement.select();
        }, 50); //select all text in any field on focus for easy re-entry. Delay sightly to allow focus to "stick" before selecting.
    });
});

