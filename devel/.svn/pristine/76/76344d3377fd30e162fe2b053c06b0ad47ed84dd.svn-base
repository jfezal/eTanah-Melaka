<%-- 
    Document   : sedia_b2_pelan
    Created on : Aug 15, 2017, 2:46:12 AM
    Author     : john
--%>


<%@ page import="java.io.*,etanah.Configuration" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<link type="text/css" href="<%= request.getContextPath()%>/pub/select/themes/base/jquery.ui.core.css" rel="stylesheet" />
<link type="text/css" href="<%= request.getContextPath()%>/pub/select/themes/base/jquery.ui.theme.css" rel="stylesheet" />
<link type="text/css" href="<%= request.getContextPath()%>/pub/select/themes/base/jquery.ui.selectmenu.css" rel="stylesheet" />
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript" src="<%= request.getContextPath()%>/pub/select/ui/jquery.ui.selectmenu.js"></script>
<style type="text/css">
    /* demo styles */
    body {font-size: 62.5%; font-family:"Verdana",sans-serif; }
    fieldset { border:0; }
    label,select,.ui-select-menu { float: left; margin-right: 10px; }
    select { width: 200px; }
    .wrap ul.ui-selectmenu-menu-popup li a { font-weight: bold; }
</style>
<script type="text/javascript">
    $(function () {

        $('select#speedD').selectmenu({
            menuWidth: 400,
            format: addressFormatting
        });


    });

    //a custom format option callback
    var addressFormatting = function (text, opt) {
        var newText = text;
        //array of find replaces
        var findreps = [
            {find: /^([^\-]+) \- /g, rep: '<span class="ui-selectmenu-item-header">$1</span>'},
            {find: /([^\|><]+) \| /g, rep: '<span class="ui-selectmenu-item-content">$1</span>'},
            {find: /([^\|><\(\)]+) (\()/g, rep: '<span class="ui-selectmenu-item-content">$1</span>$2'},
            {find: /([^\|><\(\)]+)$/g, rep: '<span class="ui-selectmenu-item-content">$1</span>'},
            {find: /(\([^\|><]+\))$/g, rep: '<span class="ui-selectmenu-item-footer">$1</span>'}
        ];

        for (var i in findreps) {
            newText = newText.replace(findreps[i].find, findreps[i].rep);
        }
        return newText;
    }
</script>
<script type="text/javascript">

    function save1(event, f) {
        if (validation()) {
        } else {
            var idPlot = $('[name=idPlot]').val();
            var syaratNyataPlot = $('[name=syaratNyataPlot]').val();
            var sekatanPlot = $('[name=sekatanPlot]').val();
            var bumiPlot = $('[name=bumiPlot]').val();
            alert(syaratNyataPlot);
            var url = "${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?simpanKelompok&idPlot=" + idPlot + "&syaratNyataPlot=" + syaratNyataPlot + "&sekatanPlot=" + sekatanPlot + "&bumiPlot=" + bumiPlot;
            $.post(url,
                    function (data) {
                        alert('s');
                    }, 'html');
        }
    }

    function validation() {
        return false;
    }
    function muatNaik(nopt, luas, unitukur) {
        var idPlot = $('[name=idPlot]').val();
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?muatNaik&idPlot=" + idPlot + "&nopt=" + nopt + "&luas=" + luas + "&unitukur=" + unitukur, "Muat Naik Pelan",
                "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");

    }
    function doViewReport(jenisPelan, id) {
//        /pelan/view/{jenisPelan}/{idPlot}/{id}
        var idPlot = $('[name=idPlot]').val();
        var url = '${pageContext.request.contextPath}/pelan/' + jenisPelan + '/' + idPlot + '/' + id;
        window.open(url, 'Pelan', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="listUtil"/>

<s:form id="senarai_tugasan" beanclass="etanah.view.stripes.common.b1.PaparanMaklumatB1ActionBean" name="form2">
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>  

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Hakmilik QT
            </legend>
            <br/>
            <s:hidden name="idPlot" />
            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center"><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/>&nbsp;
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikQt}"
                               id="line" pagesize="10" style="width:95%"
                               requestURI="${pageContext.request.contextPath}/common/b1?papar"
                               requestURIcontext="true"
                               sort="external" size="${actionBean.__pg_total_records}" partialList="true">                    
                    <c:set var="row_num" value="${row_num+1}"/>              
                    <display:column title="Bil" class="bil${line_rowNum}">                        
                        ${row_num}
                    </display:column>
                    <display:column title="ID Hakmilik QT"> 
                        ${line.idHakmilik}
                        <input type="hidden" name="idMhB1" value="${line.idMhB1}"/>
                    </display:column>
                    <display:column title="No Lot">                        
                        <input type="text" name="noLot" value="${line.noLot}"/>
                    </display:column>                    
                    <display:column title="Luas" >
                        <input type="text" name="luas" value="${line.luas}"/>
                    </display:column>
                    <display:column title="Kod Luas"><s:select  name="kodLuas" value="${line.kodLuas}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" sort="nama" />
                        </s:select></display:column>
                    <display:column title="No Pelan Akui">                        
                        <input type="text" name="noPelanAkui" value="${line.noPelanAkui}"/>
                    </display:column>
                    <display:column title="Muat Naik Pelan B1" > 
                        <s:button name="simpanSenarai" value="Muat Naik" class="btn" onclick="muatNaik('${line.noLot}','${line.luas}','${line.kodUnitLuas}');" />

                    </display:column>
                    <display:column title="Papar"> <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                         onclick="doViewReport('B1', '${line.idMhB1}');" height="30" width="30" alt="papar"
                         onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${line.noLot}"/></display:column>

                </display:table>
            </div>
            <s:submit name="simpanSenarai" value="Simpan" class="btn" />

        </fieldset>            

    </div>
</s:form>