$(function () {
    //是否存在语言项
    if (!localStorage.currentLang) {
        //本地存储当前选中语言
        localStorage.currentLang = $('#lang option:selected').val();
    } else {
        //定义当前语言
        var currLang = localStorage.currentLang;
        $("#lang option[value=" + currLang + "]").attr("selected", true);
        $("#lang").on('change', function () {
            //存储当前选中的语言
            localStorage.currentLang = $(this).children('option:selected').val();
            //刷新  单页面可以注释
            location.reload();
        });
    }
    langString(localStorage.currentLang)
});

//获得对应字段
function langString(currentLang) {
    //根据语言代码来遍历切换
    switch (currentLang) {
        case 'en':
            switchAllText(data_en);
            break;
        case 'ch':
            switchAllText(data_ch);
            break;
        default:
            switchAllText(data_ch);
            break;
    }
}

//遍历替换所有文本
function switchAllText(dataLang) {
    $('[data-action]').each(function () {
        var msg = $(this).attr('data-action');
        $(this).text(dataLang[msg]);
        // $(this).val(dataLang[msg]);
        $(this).attr('placeholder', dataLang[msg]);
    })
}