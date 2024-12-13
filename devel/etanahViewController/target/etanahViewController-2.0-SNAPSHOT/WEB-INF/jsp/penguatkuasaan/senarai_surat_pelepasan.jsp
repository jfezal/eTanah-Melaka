<%-- 
    Document   : surat_panggilan_saksi
    Created on : Sept 20, 2012, 11:24:39 AM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<c:if test="${multipleOp}">
    <style type="text/css">
        .tablecloth{
            padding:0px;
            width:90%;
        }

        .infoLP{
            float: right;
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;

        }

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>
<script type="text/javascript">
    function addRecord(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/surat_pelepasan_barang?addRecord", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,scrollbars=yes,height=700, left=" + left + ",top=" + top);
    }

    function refreshPageSuratSaksi(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/surat_pelepasan_barang?refreshpage';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }
    
    function editRecord(id){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        window.open("${pageContext.request.contextPath}/penguatkuasaan/surat_pelepasan_barang?editRecord&idKertas="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,scrollbars=yes,height=700, left=" + left + ",top=" + top);
    }
    
    function viewOKS(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }
    
    function viewSurat(id){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/4)-(150/4);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/surat_pelepasan_barang?viewRecord&idKertas='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes, left=" + left + ",top=" + top);
    }
    
    function deleteRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/surat_pelepasan_barang?deleteRecord&idKertas='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
           
        }
    }

</script>
<s:form name="form1" id="form1"  beanclass="etanah.view.penguatkuasaan.SuratPelepasanActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Surat Pelepasan
            </legend>
            <c:if test="${edit}">
                <div class="instr-fieldset"><br>
                    Klik butang tambah untuk tambah surat pelepasan barang rampasan
                </div>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiSuratSaksi}" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Tajuk">
                            <u><a class="popup" onclick="viewSurat(${line.idKertas})">${line.tajuk}</a></u>
                        </display:column>
                        <display:column title="Tarikh Masuk">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>&nbsp;
                        </display:column>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${line.idKertas}')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="deleteRecord('${line.idKertas}');"/>
                            </div>
                        </display:column>
                    </display:table>
                    <table>
                        <tr>
                            <td align="right">
                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>
                            </td>
                        </tr>
                    </table>
                </div>

            </c:if>
            <c:if test="${view}">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiSuratSaksi}" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="No.Saman">
                            <a class="popup" onclick="viewSurat(${line.idKertas})">${line.nomborRujukan}</a>
                        </display:column>
                        <display:column property="penyediaSidang" title="Nama OKS"></display:column>
                        <display:column title="Tarikh Masuk">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>&nbsp;
                        </display:column>
                    </display:table>
                </div>
            </c:if>
        </fieldset>
    </div>

</s:form>
