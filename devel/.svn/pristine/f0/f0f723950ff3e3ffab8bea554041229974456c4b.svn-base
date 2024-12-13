<%-- 
    Document   : maklumat_pemohonV2Edit
    Created on : Jun 13, 2013, 10:56:03 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maklumat Pemohon</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    function addPemohon(){

        NoPrompt();

        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormPopUp", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function editPemohon(idPemohon,idPihak)
    {
        NoPrompt();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormKemaskiniPemohon&idPemohon="+idPemohon+"&idPihak="+idPihak, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function deletePemohon(idPemohon,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?deleteRow&idPemohon='+idPemohon+'&tName='+tName,q,
                function(data){
                    $('#page_div').html(data);
                    
                }, 'html');
                    self.refreshpagePemohon('tPemohon') ;
                
            }
        }
    function showMaklumatLuarNegeri(idPemohon,idPihak,status)
    {
        NoPrompt();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?viewFormMaklumatTanahMilik&idPemohon="+idPemohon+"&idPihak="+idPihak+"&status="+status, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }    
    function editKeluarga(idPemohon)
    {
        NoPrompt();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormMaklumatKeluarga&idPemohon="+idPemohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }   
    
    function editPengarah(idPemohon)
    {
        NoPrompt();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormMaklumatPengarah&idPemohon="+idPemohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }   
        
    function refreshpage(){
        NoPrompt();
        opener.refreshV2Pemohon('main');
        self.close();
    }
    
    function refreshpagePemohon(type){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pemohon</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <th>Bil</th><th>Nama</th><th>Nombor Pengenalan</th><th>Alamat</th><th>Poskod</th><th>Negeri</th><th>Tindakan</th>
                        </tr>
                        <c:if test="${!empty actionBean.listPemohon}">
                            <c:set var="i" value="1" />
                            <tr>
                                <c:forEach items="${actionBean.listPemohon}" var="line">
                                    <td>
                                        ${i}

                                    </td>
                                    <td>
                                        ${line.pihak.nama}

                                    </td>
                                    <td>
                                        ${line.pihak.noPengenalan}
                                    </td>
                                    <td>
                                        ${line.pihak.alamat1}
                                        <c:if test="${line.pihak.alamat2 ne null}">, </c:if>
                                        ${line.pihak.alamat2}
                                        <c:if test="${line.pihak.alamat3 ne null}">, </c:if>
                                        ${line.pihak.alamat3}
                                        <c:if test="${line.pihak.alamat4 ne null}">, </c:if>
                                        ${line.pihak.alamat4}
                                    </td>
                                    <td>
                                        ${line.pihak.poskod}
                                    </td>
                                    <td>
                                        ${line.pihak.negeri.nama}
                                    </td>
                                    <td>
                                        <a onclick="deletePemohon(${line.idPemohon},this.form,'pemohon')" onmouseover="this.style.cursor='pointer';" title="Hapus Pemohon"><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                        <a onclick="editPemohon(${line.idPemohon},${line.pihak.idPihak})" onmouseover="this.style.cursor='pointer';" title="Kemaskini Pemohon"><img alt="Kemaskini Pemohon"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a> <br/>
                                        <c:if test="${line.pihak.jenisPengenalan.kod ne '0'}">
                                        <a onclick="showMaklumatLuarNegeri(${line.idPemohon},${line.pihak.idPihak},'edit')" onmouseover="this.style.cursor='pointer';" title="Maklumat Tanah Pemohon/Pemilik"><img alt="Maklumat Tanah Pemohon/Pemilik"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/land.png'/></a> 
                                        |
                                        </c:if> 
                                        <c:if test="${line.pihak.jenisPengenalan.kod eq 'B' || line.pihak.jenisPengenalan.kod eq 'L' || line.pihak.jenisPengenalan.kod eq 'P' || line.pihak.jenisPengenalan.kod eq 'T' || line.pihak.jenisPengenalan.kod eq 'I'}">
                                        <a onclick="editKeluarga(${line.idPemohon})" onmouseover="this.style.cursor='pointer';" title="Maklumat Keluarga"><img alt="Maklumat Keluarga"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/family2.png'/></a>
                                        </c:if>
                                        <c:if test="${line.pihak.jenisPengenalan.kod eq 'S'|| line.pihak.jenisPengenalan.kod eq 'U'}">
                                        <a onclick="editPengarah(${line.idPemohon})" onmouseover="this.style.cursor='pointer';" title="Maklumat Pengarah"><img alt="Maklumat Pengarah"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/corporate.png'/></a>
                                        </c:if>
                                        
                                        
                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center">
                        <s:button name="Tambah" id="save" value="Tambah Pemohon" class="longbtn" onclick="addPemohon()"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>   
        </fieldset>
    </s:form>
</body>
