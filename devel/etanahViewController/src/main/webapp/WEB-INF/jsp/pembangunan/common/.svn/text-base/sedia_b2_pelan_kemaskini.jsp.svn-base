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
            var syaratNyataPlot =$('[name=syaratNyataPlot]').val();
            var sekatanPlot =$('[name=sekatanPlot]').val();
            var bumiPlot=$('[name=bumiPlot]').val();
            var url = "${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?simpanKelompok&idPlot="+idPlot+"&syaratNyataPlot="+syaratNyataPlot+"&sekatanPlot="+sekatanPlot+"&bumiPlot="+bumiPlot;
      $.post(url,
              function(data) {
              }, 'html'); }
    }
function muatNaik(nopt,luas,unitukur) {
            var idPlot = $('[name=idPlot]').val();
       window.open("${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?muatNaik&idPlot="+idPlot+"&nopt="+nopt+"&luas="+luas+"&unitukur="+unitukur, "Muat Naik Pelan",
                "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
    
    }

    function validation() {
        return false;
    }
     function doViewReport(jenisPelan, id) {
//        /pelan/view/{jenisPelan}/{idPlot}/{id}
        var idPlot = $('[name=idPlot]').val();
        var url = '${pageContext.request.contextPath}/pelan/' + jenisPelan + '/' + idPlot + '/' + id;
        window.open(url, 'Pelan', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
</script>
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>

<s:form id="senarai_tugasan" beanclass="etanah.view.stripes.pembangunan.pelan.MuatNaikPelanB2ActionBean" name="form2">
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>  
    <s:hidden name="idPlot" id="idPlot"/>
    <fieldset class="aras1">
        <legend>
            Maklumat Hakmilik Baru
        </legend>

        <p>
            <label>Syarat Nyata :</label>
            <s:select name="syaratNyataPlot" id="speedD" >
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${actionBean.listSyaratNyata}" label="nama" value="kod" sort="nama" />
            </s:select>
        </p>
        <p>
            <label for="speedD">Sekatan Kepentingan :</label>
            <s:select name="sekatanPlot" id="speedD" >
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${actionBean.listSekatan}" label="nama" value="kod" sort="nama" />
            </s:select>
        </p>
        <p>
            <label>Bumiputra :</label>
            <s:select name="bumiPlot" id="speedD" >
                <s:option value="T">Tidak</s:option>
                <s:option value="Y">Ya</s:option>
            </s:select>
        </p>

        <p>
            <label>&nbsp;</label>
            <s:button name="simpanKelompok" value="Kemaskini" class="btn" onclick="save1(this.name, this.form);" />
        </p>
    </fieldset>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Plot
            </legend>
            <br/>

            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center"><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/>&nbsp;
                <display:table class="tablecloth" name="${actionBean.senaraiMuatNaik}"
                               id="line" pagesize="10" style="width:95%"
                               requestURI="${pageContext.request.contextPath}/pembangunan/melaka/muat_naik_pelan?papar"
                               requestURIcontext="true"
                               sort="external" size="${actionBean.__pg_total_records}" partialList="true">                    
                    <c:set var="row_num" value="${row_num+1}"/>              

                    <display:column title="Bil" class="bil${line_rowNum}">                        

                        ${row_num}
                    </display:column>
                    <display:column title="No Plot">                        
                        ${line.noPlot} 
                        <input type="hidden" name="idPt" value="${line.idPt}"/>
                    </display:column>

                    <display:column title="No Pt">                        
                        ${line.noPT}                       
                    </display:column>                    
                    <display:column title="Luas" >${line.luas} ${line.unitLuas}</display:column>
                    <display:column title="Syarat Nyata">                        
                        <s:select name="syaratNyata" id="speedD" value="${line.syaratNyata}">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listSyaratNyata}" label="nama" value="kod" sort="nama" />
                        </s:select>                    
                    </display:column>
                    <display:column title="Syarat Kepentingan">                        
                        <s:select name="sekatan" id="speedD" value="${line.sekatan}">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listSekatan}" label="nama" value="kod" sort="nama" />
                        </s:select>                   
                    </display:column>
                    <display:column title="Bumiputra">                        
                        <s:select name="bumi" id="speedD" value="${line.bumi}">
                            <s:option value="T">Tidak</s:option>
                            <s:option value="Y">Ya</s:option>
                        </s:select>                     
                    </display:column>
                    <c:if test="${!actionBean.view}">
                        <display:column title="Muat Naik Pelan B2" >            
                            <s:button name="simpanSenarai" value="Muat Naik" class="btn" onclick="muatNaik('${line.noPT}','${line.luas}','${line.kodUnitLuas}');" />

                        </display:column>
                    </c:if>
                    <display:column title="Papar"> <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                         onclick="doViewReport('B2', '${line.idPt}');" height="30" width="30" alt="papar"
                         onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${line.noPT}"/></display:column>

                </display:table>
            </div>
            <s:submit name="simpanSenarai" value="Simpan" class="btn"/>

        </fieldset>            
        
</s:form>