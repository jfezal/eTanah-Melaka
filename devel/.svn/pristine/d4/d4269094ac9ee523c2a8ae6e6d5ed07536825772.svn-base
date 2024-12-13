<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    var date = new Date();
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function doSubmit(f){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        var report = 'ACQBayaranDendaLewat_MLK.rdf';
        var z = '${idmohon}';
        if(report !== null){

    <%-- f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
     f.submit();--%>

                 window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_mohon="+z, "eTanah",
                 "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                 $.unblockUI();
             }
         }


         function dateValidation(id, value){
             var vsplit = value.split('/');
             var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
             var today = new Date();
             var sdate = new Date(fulldate);
             if (sdate > today){
                 alert("Tarikh yang dimasukkan tidak sesuai.");
                 $('#'+id).val("");
             }
         }


</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.stripes.pengambilan.DendaLewat">
    <div id="display" class="subtitle">
        <fieldset class="aras1">
            <legend>
                JADUAL BAYARAN CAJ DENDA LEWAT 8%
            </legend>

            <p>
                <label>ID Permohonan :</label>
                <s:text id = "idmohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>&nbsp;
            </p>
            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Cari" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
            </p>

        </fieldset >
    </div>
</s:form>
