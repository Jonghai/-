/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.allowedContent = true;
	config.toolbar = [
        ['Font', 'FontSize'],
        ['Bold', 'Italic', 'Strike', 'Underline'],   
        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
        ['SpecialChar', 'Smiley'],
        ['Blockquote', 'NumberedList', 'BulletedList'],
        ['Link', 'Unlink'],
        ['Undo', 'Redo']
];
};

CKEDITOR.on( 'dialogDefinition', function( ev ){
	 
    var dialogName = ev.data.name;
    var dialogDefinition = ev.data.definition;
    
    if ( dialogName == 'image' ){
        
        dialogDefinition.removeContents( 'Link' );    //링크 탭 제거
        dialogDefinition.removeContents( 'advanced' );  //상세정보 탭 제거
        
        var infoTab = dialogDefinition.getContents( 'info' );  //info탭을 제거하면 이미지 업로드가 안된다.
        
    }
});

