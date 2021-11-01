/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {

	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config


	// Setup
	// ------------------------------

    // RTL version uses different config file
    
	config.skin = 'default';

    // Load content styles
	config.contentsCss = '/js/plugins/ckeditor/skins/default/contents.css';


	// Toolbar
	// ------------------------------

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];


	// Extra config
	// ------------------------------

	// Set the most common block elements.
	//config.format_tags = 'p;h1;h2;h3;h4;h5;h6;pre';

	// Allow content rules
	config.uploadUrl = "/admin/dragDropUploadAjax.do?type=board";
	//config.uploadUrl = "/common/ckEditorImageUpload.do";
	config.image_previewText = " ";
	config.autoParagraph = false;
	config.allowedContent = true;

	// Allow any class and any inline style
	config.extraAllowedContent = '*(*);*{*}';
	config.filebrowserUploadUrl = "/admin/dragDropUploadAjax.do?type=board";
	//config.extraAllowedContent = 'p(*)[*]{*};div(*)[*]{*};li(*)[*]{*};ul(*)[*]{*}';
	// endter / shiftEnter Mode
	//config.enterMode = CKEDITOR.ENTER_BR;
	//config.shiftEnterMode = CKEDITOR.ENTER_P;
	
	//config.basicEntities = false;
	//config.htmlEncodeOutput = false;
	//config.entities = false;
};



CKEDITOR.dtd.$removeEmpty.i = 0;
CKEDITOR.dtd.$removeEmpty.span = 0;
