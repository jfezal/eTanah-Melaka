<%--
    Document   : maklumat_senarai_bantahan
    Created on : 04-March-2013, 12:27:21
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
  
    function addRecord(id,status){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_bantahan?addRecord&idMohonPihakBantah="+id+"&statusView="+status, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_bantahan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function test(f) {
        $(f).clearForm();
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function removeRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            alert(id);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_bantahan?deleteRecord&id='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBantahanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Senarai Bantahan
            </legend>
            <div class="content" align="center">
                <display:table name="${actionBean.listPihakMembantah}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Nama">
                        <a class="popup" onclick="addRecord('${line.idMohonPihakBantah}','view')">${line.nama}</a>
                    </display:column>
                    <display:column title="Kategori Pembantah">
                        <c:if test="${line.kategoriPihakMembantah eq 'L'}">Lain-lain</c:if>
                        <c:if test="${line.kategoriPihakMembantah eq 'P'}">Pihak Berkepentingan</c:if>
                    </display:column>
                    <display:column title="No.Pengenalan" property="noPengenalan"/>
                    <display:column title="Tarikh Bantahan"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhBantahan}"/></display:column>
                    <display:column title="Status Waris">
                        <c:if test="${line.statusWaris eq 'Y'}">Ya</c:if>
                        <c:if test="${line.statusWaris eq 'T'}">Tidak</c:if>
                    </display:column>
                    <c:if test="${edit}">
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="addRecord('${line.idMohonPihakBantah}','edit')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus" >
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeRecord('${line.idMohonPihakBantah}');"/>
                            </div>
                        </display:column>
                    </c:if>

                </display:table>
                <c:if test="${edit}">
                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord('','add');"/>
                            </td>
                        </tr>
                    </table>

                </c:if>


                <br>
            </div>
        </fieldset>
    </div>
</s:form>
