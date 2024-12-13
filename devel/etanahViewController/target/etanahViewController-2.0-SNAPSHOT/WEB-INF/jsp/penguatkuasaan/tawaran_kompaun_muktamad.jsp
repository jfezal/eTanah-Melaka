<%--
    Document   : tawaran_kompaun_muktamad
    Created on : May 13, 2011, 11:52:22 AM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript">
    function refreshPageKompaun(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function viewMuktamadKompaun(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?viewMuktamadKompaunDetail&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }

    function refreshMuktamadKompaun(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kompaun?refreshPageMuktamadkompaun';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }


</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKompaunActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Muktamad Kompaun
            </legend>
            <br>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font> Sila klik No.Siri untuk memasukkan maklumat muktamad kompaun.
            </div>&nbsp;
            <div class="content" align="center">
                <div class="content" align="left">

                </div>
                <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true">
                        ${line_rowNum}
                    </display:column>
                    <display:column title="No.Siri">
                        <a class="popup" onclick="viewMuktamadKompaun('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                    </display:column>
                    <display:column title="Muktamad Kompaun (RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                    <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                    <display:column title="Diserahkan Kepada">${line.isuKepada}, ${line.noPengenalan}</display:column>

                </display:table>

                <br>


            </div>
        </fieldset>
    </div>
</s:form>
