<%-- 
    Document   : maklumat_pemohonV2EditKeluarga
    Created on : Jul 16, 2013, 11:30:43 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maklumat Keluarga</title>
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
    function addPemohonHubungan(type){
        var idPemohon = $('#idPemohon').val();
        NoPrompt();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormTambahPemohonHubungan&type="+type+"&idPemohon="+ idPemohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function editPemohonHubungan(idPemohonHubungan,type)
    {
        NoPrompt();
        var idPemohon = $('#idPemohon').val();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormKemaskiniPemohonHubungan&idPemohonHubungan="+idPemohonHubungan+"&type="+type+"&idPemohon="+idPemohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function deleteRow(idPemohonHubungan,f,tName,idPemohon)
    {   
        NoPrompt();
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?deleteRow&idPemohon='+idPemohonHubungan+'&tName='+tName,q,
            function(data){
                $('#page_div').html(data);
                self.editKeluarga(idPemohon) ;
            }, 'html');    
        }
    }
    function editKeluarga(idPemohon)
    {
        //        NoPrompt();
        // alert(idPemohon);
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?showFormMaklumatKeluarga&idPemohon="+idPemohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }     
    
    function refreshpagePemohon(type){
        //        NoPrompt() ;
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
        <s:hidden name="idPemohon" id="idPemohon"/>
        <c:if test="${actionBean.pemohon.statusKahwin eq 'Berkahwin' }">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Suami/Isteri</legend>
                    <div class="content" align="center">

                        <table class="tablecloth" border="0">
                            <tr>
                                <th>Bil</th><th>Nama</th><th>Nombor Pengenalan</th><th>Kaitan</th><th>Alamat</th><th>Poskod</th><th>Negeri</th><th>Tindakan</th>
                            </tr>
                            <c:if test="${!empty actionBean.listHubunganSuamiIsteri}">
                                <c:set var="i" value="1" />
                                <tr>
                                    <c:forEach items="${actionBean.listHubunganSuamiIsteri}" var="line">
                                        <td>
                                            ${i}

                                        </td>
                                        <td>
                                            ${line.nama}

                                        </td>
                                        <td>
                                            ${line.noPengenalan}
                                        </td>
                                        <td>
                                            ${line.kaitan}
                                        </td>
                                        <td>
                                            ${line.alamat1}
                                            <c:if test="${line.alamat2 ne null}">, </c:if>
                                            ${line.alamat2}
                                            <c:if test="${line.alamat3 ne null}">, </c:if>
                                            ${line.alamat3}
                                            <c:if test="${line.alamat4 ne null}">, </c:if>
                                            ${line.alamat4}
                                        </td>
                                        <td>
                                            ${line.poskod}
                                        </td>
                                        <td>
                                            ${line.negeri.nama}
                                        </td>
                                        <td>
                                            <a onclick="deleteRow(${line.idHubungan},this.form,'pemohonHubungan',${line.pemohon.idPemohon})" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                            <a onclick="editPemohonHubungan(${line.idHubungan},'tSuamiIsteri')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini Pendeposit"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>

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
        </c:if>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Ibu Bapa</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <th>Bil</th><th>Nama</th><th>Nombor Pengenalan</th><th>Kaitan</th><th>Alamat</th><th>Poskod</th><th>Negeri</th><th>Tindakan</th>
                        </tr>
                        <c:if test="${!empty actionBean.listHubunganIbuBapa}">
                            <c:set var="i" value="1" />
                            <tr>
                                <c:forEach items="${actionBean.listHubunganIbuBapa}" var="line">
                                    <td>
                                        ${i}

                                    </td>
                                    <td>
                                        ${line.nama}

                                    </td>
                                    <td>
                                        ${line.noPengenalan}
                                    </td>
                                    <td>
                                        ${line.kaitan}
                                    </td>
                                    <td>
                                        ${line.alamat1}
                                        <c:if test="${line.alamat2 ne null}">, </c:if>
                                        ${line.alamat2}
                                        <c:if test="${line.alamat3 ne null}">, </c:if>
                                        ${line.alamat3}
                                        <c:if test="${line.alamat4 ne null}">, </c:if>
                                        ${line.alamat4}
                                    </td>
                                    <td>
                                        ${line.poskod}
                                    </td>
                                    <td>
                                        ${line.negeri.nama}
                                    </td>
                                    <td>
                                        <a onclick="deleteRow(${line.idHubungan},this.form,'pemohonHubungan',${line.pemohon.idPemohon})" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                        <a onclick="editPemohonHubungan(${line.idHubungan},'tIbuBapa')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini Pendeposit"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>

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
        <c:if test="${actionBean.pemohon.statusKahwin eq 'Janda' || actionBean.pemohon.statusKahwin eq 'Duda' || actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Anak</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <th>Bil</th><th>Nama</th><th>Nombor Pengenalan</th><th>Alamat</th><th>Poskod</th><th>Negeri</th><th>Tindakan</th>
                            </tr>
                            <c:if test="${!empty actionBean.listHubunganAnak}">
                                <c:set var="i" value="1" />
                                <tr>
                                    <c:forEach items="${actionBean.listHubunganAnak}" var="line">
                                        <td>
                                            ${i}

                                        </td>
                                        <td>
                                            ${line.nama}

                                        </td>
                                        <td>
                                            ${line.noPengenalan}
                                        </td>
                                        <td>
                                            ${line.alamat1}
                                            <c:if test="${line.alamat2 ne null}">, </c:if>
                                            ${line.alamat2}
                                            <c:if test="${line.alamat3 ne null}">, </c:if>
                                            ${line.alamat3}
                                            <c:if test="${line.alamat4 ne null}">, </c:if>
                                            ${line.alamat4}
                                        </td>
                                        <td>
                                            ${line.poskod}
                                        </td>
                                        <td>
                                            ${line.negeri.nama}
                                        </td>
                                        <td>
                                            <a onclick="deleteRow(${line.idHubungan},this.form,'pemohonHubungan',${line.pemohon.idPemohon})" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                            <a onclick="editPemohonHubungan(${line.idHubungan},'tAnak')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini Pendeposit"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>

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
        </c:if>


        <div class="subtitle">
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <c:if test="${actionBean.pemohon.statusKahwin eq 'Janda' || actionBean.pemohon.statusKahwin eq 'Duda' || actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
                                <s:button name="Tambah" id="save" value="Tambah Suami/Isteri" class="longbtn" onclick="addPemohonHubungan('tSuamiIsteri')"/>
                            </c:if>               
                            <s:button name="Tambah" id="save" value="Tambah Ibu Bapa" class="longbtn" onclick="addPemohonHubungan('tIbuBapa')"/>
                            <c:if test="${actionBean.pemohon.statusKahwin eq 'Janda' || actionBean.pemohon.statusKahwin eq 'Duda' || actionBean.pemohon.statusKahwin eq 'Berkahwin'}">
                                <s:button name="Tambah" id="save" value="Tambah Anak" class="longbtn" onclick="addPemohonHubungan('tAnak')"/>
                            </c:if>                          
                            <s:button name="tutup" value="Kembali" class="btn" onclick="refreshpagePemohon('tPemohon')"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>

    </s:form>
</body>

