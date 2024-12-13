<%-- 
    Document   : minit_arahan_lucuthak
    Created on : Feb 23, 2010, 2:51:09 PM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/maklumat_mesyuarat?popup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function removeSingle(idRujukan)
    {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/maklumat_mesyuarat?deleteSingle&idRujukan='
                +idRujukan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
        self.opener.refreshPagePantau1();
    }
    function refreshPageMesy(){
        var url = '${pageContext.request.contextPath}/maklumat_mesyuarat?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function popup(h){
        var url = '${pageContext.request.contextPath}/maklumat_mesyuarat?popupedit&idRujukan='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");


    }


</script>
<s:form beanclass="etanah.view.penguatkuasaan.LucuthakActionBean">
<div class="subtitle">
    <fieldset class="aras1" id="locate">
            <legend>
                Minit Arahan Lucuthak
            </legend>
             <c:if test="${edit}">
                <div class="instr-fieldset">
                    Klik butang tambah untuk masukkan maklumat minit arahan lucuthak
                </div>
                <div class="content" align="center">
                    <%--name="${actionBean.senaraiAduanPemantauan}" --%>
                    <display:table class="tablecloth" name="${actionBean.senaraiMesyuarat}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Tarikh Mesyuarat/Lucuthak" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/></display:column>
                        <display:column title="Masa Mesyuarat/Lucuthak" style="${bcolor}"><fmt:formatDate value="${line.tarikhSidang}" pattern="hh:mm:ss aaa" /></display:column>
                        <display:column title="Keputusan" property="catatan"></display:column>
                         <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idRujukan}')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiMesyuarat[line_rowNum-1].idRujukan}');" />
                            </div>
                        </display:column>

                    </display:table>
                    <br>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>

                </div>
            </c:if>
            <c:if test="${view}">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiMesyuarat}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Tarikh Mesyuarat/Lucuthak" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhSidang}"/></display:column>
                        <display:column title="Masa Mesyuarat/Lucuthak" style="${bcolor}"><fmt:formatDate value="${line.tarikhSidang}" pattern="hh:mm:ss aaa" /></display:column>
                        <display:column title="Keputusan" property="catatan"></display:column>
                    </display:table>
                    <br>
                </div>
            </c:if>
       </fieldset>
    </div>
</s:form>
