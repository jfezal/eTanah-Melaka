<%-- 
    Document   : ulasan_jabatan_teknikal
    Created on : Apr 8, 2010, 2:28:55 PM
    Author     : nursyazwani
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">

    function addRow(){

        var rowNo = $('table#line1 tr').length;
        $('table#line1 > tbody').append("<tr id='x"+rowNo+"' class='x'>\n\
                <td class='rowNo'>"+rowNo+"</td>\n\
                <td><select name=jabatanArray style=width:400px>"+getOption()+"</select></td><td><textarea name=ulasanArray rows=3 cols=40/></td>\n\
                </tr>");
    }
    function getOption(){
        var option='';
        var senaraiJabatan = document.getElementById('jabatan');
        for (var i = 0; i < senaraiJabatan.options.length; ++i){
            option = option + '<option value='+senaraiJabatan.options[i].value+'>'+senaraiJabatan.options[i].text+'</option>';
        }
        return option;
    }
    function addNewUlasan(){
            window.open("${pageContext.request.contextPath}/pembangunan/ulasan_jabatan_teknikal?ulasanPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function refreshPageUlasanJabatanTeknikal(){
    var url = '${pageContext.request.contextPath}/pembangunan/ulasan_jabatan_teknikal?refreshpage';
    $.get(url,
    function(data){
    $('#page_div').html(data);
    },'html');
    }
    function removeSingle(val)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pembangunan/ulasan_jabatan_teknikal?deleteSingle&idKertas='+val;
            $.get(url,
            function(data){
            $('#page_div').html(data);
            self.opener.refreshPageUlasanJabatanTeknikal();
            },'html');
        }
    }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.UlasanJabatanTeknikalActionBean">
    <s:messages/>
    <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan Jabatan Teknikal</legend>
                <div class="content" align="center">
                    <display:table name="${actionBean.senaraiKertas}" id="line1" class="tablecloth"
                                   pagesize="12" cellpadding="0" cellspacing="0" requestURI="/pembangunan/ulasan_jabatan_teknikal">
                        <display:column title="No">
                            ${line1_rowNum}
                        </display:column>
                          <display:column title="Jabatan">
                            <c:forEach items="${line1.senaraiKandungan}" var="senarai">
                                <c:out value="${senarai.subtajuk}"/><br>
                            </c:forEach>
                        </display:column>
                        <display:column title="Ulasan">
                            <c:forEach items="${line1.senaraiKandungan}" var="senarai">
                                <c:out value="${senarai.kandungan}"/><br>
                            </c:forEach>
                        </display:column>
                        <display:column title="Hapus">
                           <div align="center">
                           <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                            id='rem_${line1_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${line1.idKertas}');" />
                           </div>
                    </display:column>
                    </display:table>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="addNewUlasan();"/>&nbsp;
                </div>
        </fieldset >
    </div>
</s:form>
