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
    function janas(idSkimStrata) {

        var url = "${pageContext.request.contextPath}/strata/jana_cukai_petak?janaCukai&idSkimStrata=" + idSkimStrata;
        $.post(url,
                function(data) {
                     if(data != ''){
                                             alert('Cukai Telah Berjaya Di Jana');
//                        $('#reload').html(data);
                         var url = '${pageContext.request.contextPath}/strata/jana_cukai_petak';
        window.location = url;
                    }
                }, 'html');

    }
    function perincianHakmilik(idHakmilikInduk) {
//        alert(idHakmilikInduk);
        window.open("${pageContext.request.contextPath}/strata/jana_cukai_petak?perincianHakmilik&idHakmilikInduk=" + idHakmilikInduk, "eTanah",
                "status=0,location=0,menubar=0,width=1000,height=1000");
    }

    function dopopup(idHakmilikInduk) {

        window.open("${pageContext.request.contextPath}/strata/jana_cukai_petak?perincianHakmilik&idHakmilikInduk" + idHakmilikInduk, "eTanah",
                "status=0,location=0,menubar=0,width=1000,height=1000");
    }
    function selectAllHakmilik(a) {
//                alert("selectall");
        //alert(a);
        for (i = 0; i < 100; i++) {
            var c = document.getElementById("checkboxhakmilikInduk" + i);
            //alert(c);
            if (c == null)
                break;
            c.checked = a.checked;
        }
    }

    function kemaskiniHakmilik() {
        if (confirm('Adakah anda pasti untuk kemaskini Hakmilik ini')) {
            var id = $('#hiddenIdHakmilik').val();
            var param = '';
            $('.allhakmilik').each(function(index) {
                var a = $('#checkboxhakmilikInduk' + index).is(":checked");
                if (a) {
                    param = param + '&senaraiSkim=' + $('#checkboxhakmilikInduk' + index).val();
                }
            });

            if (param == '') {
                alert('Sila Pilih hakmilik terlebih dahulu.');
                return;
            }

            var url = '${pageContext.request.contextPath}/strata/jana_cukai_petak?janaBerkumpulan' + param + '&idHakmilik=' + id;
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        $.unblockUI();
                        var url = '${pageContext.request.contextPath}/strata/jana_cukai_petak';
        window.location = url;
                    }, 'html');
        }

        function p(v) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top: ($(window).height() - 50) / 2 + 'px',
                    left: ($(window).width() - 50) / 2 + 'px',
                    width: '50px'
                }
            });

            $.get("${pageContext.request.contextPath}/strata/jana_cukai_petak?perincianHakmilik&idHakmilik=" + v,
                    function(data) {
                        //alert(data);
                        $("#perincianHakmilik").show();
                        $("#perincianHakmilik").html(data);
                        $.unblockUI();
                        var url = '${pageContext.request.contextPath}/strata/jana_cukai_petak';
        window.location = url;
                    });

        }
    }
</script>
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="listUtil"/>

<s:form id="senarai_tugasan" beanclass="etanah.view.strata.cukaipetak.JanaCukaiPetakActionBean" name="form2">
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>  

    <div class="subtitle" id="reload">
        <fieldset class="aras1">
            
               <legend>
                Carian Skim Strata
            </legend>
            <p><label>Id Hakmilik</label> <s:text name="idHakmilik" size="40"/> </p>
            
            <p><label>&nbsp;</label>  <s:submit class="longbtn" value="cari" name="cari"/>&nbsp;
            </p>
            <br>
           
            

        </fieldset> 
        <fieldset class="aras1">
            <legend>
                Senarai Skim Strata
            </legend>
            <br/>
            <s:hidden name="idPlot" />
            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center" id="listhakmilik">
                <img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/>&nbsp;
                <display:table class="tablecloth" name="${actionBean.senaraiSkim}"
                               id="line" pagesize="10" style="width:95%"
                               requestURI="${pageContext.request.contextPath}/strata/jana_cukai_petak"
                               requestURIcontext="true"
                               sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                    <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAllHakmilik(this);'>">
                        <s:checkbox name="checkboxhakmilikInduk" id="checkboxhakmilikInduk${line_rowNum-1}" value="${line.id}" class="allhakmilik"/>
                    </display:column>
                    <c:set var="row_num" value="${row_num+1}"/>              
                    <display:column title="Bil" class="bil${line_rowNum}">                        
                        ${row_num}
                    </display:column>
                    <display:column title="ID Hakmilik Master">
                        <a href="#" onclick="perincianHakmilik('${line.idHakmilikInduk}');">
                            ${line.idHakmilikInduk}
                        </a>

                        <input type="hidden" name="id" value="${line.id}"/>
                    </display:column>

                    <display:column title="Syarat Nyata " >
                        ${line.katgBangunanNama}
                    </display:column>
                    <display:column title="Pengkelasan Tanah" >
                        ${line.kelasTanahNama}
                    </display:column>
                    <display:column title="Status">
                        <s:button name="jana" value="Jana" class="btn" onclick="janas('${line.id}');"/>
                    </display:column>


                </display:table>
            </div>
            <center>  <s:button class="longbtn" value="Jana Berkumpulan" name="" onclick="kemaskiniHakmilik();" disabled="${disabledbtn}"/>&nbsp;</center>

        </fieldset>            

    </div>
</s:form>