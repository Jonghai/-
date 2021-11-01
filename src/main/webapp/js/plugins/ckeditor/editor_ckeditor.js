/* ------------------------------------------------------------------------------
 *
 *  # CKEditor editor
 *
 *  Demo JS code for editor_ckeditor.html page
 *
 * ---------------------------------------------------------------------------- */


// Setup module
// ------------------------------

var CKEditor = function() {

    var _componentCKEditor = function() {
        if (typeof CKEDITOR == 'undefined') {
            console.warn('Warning - ckeditor.js is not loaded.');
            return;
        }

        CKEDITOR.config.customConfig = 'config.js';       
    };

    return {
        init: function() {
            _componentCKEditor();
        }
    }
}();


// Initialize module
// ------------------------------

document.addEventListener('DOMContentLoaded', function() {
    CKEditor.init();
});
