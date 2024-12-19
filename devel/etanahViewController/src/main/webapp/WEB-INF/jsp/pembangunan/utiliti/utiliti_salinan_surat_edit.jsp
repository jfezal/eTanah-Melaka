<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Salinan-salinan Kepada</title>
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
    function addSalinan(){
        var kodDokumen = $('#kodDokumen').val();
        var idPermohonan = $('#idPermohonan').val() ;
        //        alert(kodDokumen);
        NoPrompt();

        window.open("${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?showFormTambahSalinan&idPermohonan="+idPermohonan+"&kodDokumen="+kodDokumen, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function editSalinan(idSalinan)
    {
        var kodDokumen = $('#kodDokumen').val();
        var idPermohonan = $('#idPermohonan').val() ;
        NoPrompt();
        window.open("${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?showFormEditSalinan&idSalinan="+idSalinan+"&idPermohonan="+idPermohonan+"&kodDokumen="+kodDokumen, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function deleteSalinan(idSalinan,f,tName)
    {   
//        alert("X");
        NoPrompt();
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?deleteRow&idKandungan='+idSalinan+'&tName='+tName,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
                
            self.opener.refreshPageSalinan();
        }
    }

    function refreshPageSalinan(){
        alert("Data Telah Dipadam");
        var kodDokumen = $('#kodDokumen').val();
        var idPermohonan = $('#idPermohonan').val() ;
        var url = '${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?showFormSalinan&idPermohonan='+idPermohonan+'&kodDokumen='+kodDokumen;
        $.get(url,
        function(data){
             $("#edit").replaceWith($('#edit', $(data)));
        },'html');
       
//        window.open("${pageContext.request.contextPath}/utiliti/utilitiSalinanDev?showFormSalinan&idPermohonan="+idPermohonan+"&kodDokumen="+kodDokumen, "eTanah",
//        "status=0,toolbar=0,location=0,menubar=0,width=900,height=500,scrollbars=yes");
    }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        //        window.onbeforeunload = WarnUser;
        //        function WarnUser()
        //        {   
        //            if(allowPrompt)
        //                refreshPageSalinan();
        //            if(allowPrompt)
        //            {
        //                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
        //            }
        //            else
        //            {
        //                // Reset the flag to its default value.
        //                allowPrompt = true;
        //            }
        //        }
        function NoPrompt()
        {
            allowPrompt = false;
        }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiSalinanKepadaDevActionBean" name ="senaraiSalinan" id ="senaraiSalinan">
        <s:messages/>
        <s:errors/>
        <div class="subtitle">
            <s:hidden name="idPermohonan" id="idPermohonan"/>
            <s:hidden name="kodDokumen" id="kodDokumen"/>
            <fieldset class="aras1">
                <div id ="edit">
                <legend>Maklumat Salinan Kepada</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <th>Bil</th><th>Nama</th><th>Alamat</th><th>Poskod</th><th>Negeri</th><th>Tindakan</th>
                        </tr>
                        <c:if test="${!empty actionBean.listSalinanKepada}">
                            <c:set var="i" value="1" />
                            <tr>
                                <c:forEach items="${actionBean.listSalinanKepada}" var="line">
                                    <td>
                                        ${i}
                                    </td>
                                    <td>
                                        ${line.nama}
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
                                        <c:if test="${line.negeri.kod eq '01'}">Johor</c:if>
                                        <c:if test="${line.negeri.kod eq '02'}">Kedah</c:if>
                                        <c:if test="${line.negeri.kod eq '03'}">Kelantan</c:if>
                                        <c:if test="${line.negeri.kod eq '04'}">Melaka</c:if>
                                        <c:if test="${line.negeri.kod eq '05'}">Negeri Sembilan</c:if>
                                        <c:if test="${line.negeri.kod eq '06'}">Pahang</c:if>
                                        <c:if test="${line.negeri.kod eq '07'}">Pulau Pinang</c:if>
                                        <c:if test="${line.negeri.kod eq '08'}">Perak</c:if>
                                        <c:if test="${line.negeri.kod eq '09'}">Perlis</c:if>
                                        <c:if test="${line.negeri.kod eq '10'}">Selangor</c:if>
                                        <c:if test="${line.negeri.kod eq '11'}">Terengganu</c:if>
                                        <c:if test="${line.negeri.kod eq '12'}">Sabah</c:if>
                                        <c:if test="${line.negeri.kod eq '13'}">Sarawak</c:if>
                                        <c:if test="${line.negeri.kod eq '14'}">WP Kuala Lumpur</c:if>
                                        <c:if test="${line.negeri.kod eq '15'}">WP Labuan</c:if>
                                        <c:if test="${line.negeri.kod eq '16'}">Luar Negara</c:if>
                                        <c:if test="${line.negeri.kod eq '17'}">WP Putrajaya</c:if>
                                        <c:if test="${line.negeri.kod eq '99'}">Lain-lain</c:if>
                                    </td>
                                    <td>
                                        <a onclick="deleteSalinan(${line.idSalinanKepada},this.form,'salinanKepada')" onmouseover="this.style.cursor='pointer';" title="Hapus Salinan"><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                        <a onclick="editSalinan(${line.idSalinanKepada})" onmouseover="this.style.cursor='pointer';" title="Kemaskini Salinan"><img alt="Kemaskini Salinan"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a> <br/>
                                    </td>
                                </tr>

                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                        </c:if>
                    </table>
                    <%--
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listSalinanKepada}"
                                       id="line" style="width:95%"
                                       requestURI="${pageContext.request.contextPath}/utiliti/utilitiSalinan">                    
                            <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                                ${line_rowNum}
                            </display:column>
                            <display:column property="negeri.nama" title="Negeri"/>
                            <display:column property="nama" title="Nama"/>
                            <display:column title="Tindakan">
                                <a onclick="openSalinanKepada('${actionBean.idPermohonan}','${line.kodDokumen.kod}')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                </display:column>
                            </display:table>
                    </div>
                    --%>
                </div>
                <br/>
                </div>
            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center">
                        <s:button name="Tambah" id="save" value="Tambah Salinan" class="longbtn" onclick="addSalinan()"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </td>
                </tr>
            </table>   
        </fieldset>     
    </s:form>
</body>

