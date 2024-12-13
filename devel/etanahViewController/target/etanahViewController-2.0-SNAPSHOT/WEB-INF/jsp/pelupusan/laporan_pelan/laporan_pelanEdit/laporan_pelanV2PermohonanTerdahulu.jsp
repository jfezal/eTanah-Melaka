<%-- 
    Document   : laporan_pelanV2PermohonanTerdahulu
    Created on : Feb 19, 2013, 2:48:36 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Permohonan Terdahulu</title>
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
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
    
    
    function refreshpage(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                var idHakmilik = $('#idHakmilik').val();
                opener.refreshV2ManyHakmilik('main',idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
            
            self.close();
        }
        
        function deleteMohonTerdahulu(idMohonManual,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?deleteRow&idKand='+idMohonManual+'&tName='+tName,q,
                function(data){
                    $('#page_div').html(data);
                    
                }, 'html');
                
                self.refreshpage2('pTerdahulu') ;
            }
        }
        function updateMohonTerdahulu(idMohonManual,f,tName)
        {
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?updateRow&idKand="+idMohonManual+"&tName="+tName, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function refreshpage2(type){
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?refreshpage&type='+type;
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

        function addFail(){
    <%--alert(id);--%>
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
            //            alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?showFormNoFail&idHakmilik="+idHakmilik+"&noPtSementara="+noPtSementara, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function addPermohonan(){
    <%--alert(id);--%>
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
            alert(idHakmilik);
            alert(noPtSementara);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?showFormPopUp&idHakmilik="+idHakmilik+"&noPtSementara="+noPtSementara, "eTanah",
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
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanPelanV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="kodD" id="kodD"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.permohonanLaporanPelan.idLaporan}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Permohonan Terdahulu</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <th>Id Permohonan/No Fail</th><th>Urusan</th><th>Status Keputusan</th><th>Keputusan Oleh</th><th>Tarikh Keputusan</th><th>Nama Pemohon</th><th>Tindakan</th>
                        </tr>
                        <c:if test="${!empty actionBean.listpermohonanTanahTerdahulu}">
                            <c:set var="i" value="1" />
                            <tr>
                                <c:forEach items="${actionBean.listpermohonanTanahTerdahulu}" var="line">
                                    <c:if test="${line.permohonanTerdahulu ne null}">
                                        <td>
                                            ${line.permohonanTerdahulu.idPermohonan}

                                        </td>
                                    </c:if>
                                    <c:if test="${line.permohonanTerdahulu eq null}">
                                        <td>
                                            ${line.noFail}

                                        </td>
                                    </c:if>
                                    <td>
                                        ${line.permohonanTerdahulu.kodUrusan.nama}

                                    </td>
                                    <td>
                                        ${line.statusKeputusan}
                                    </td>
                                    <td>
                                        ${line.keputusanOleh}
                                    </td>
                                    <td>
                                        <s:format value="${line.tarikhKeputusan}" formatPattern="dd/MM/yyyy"/>
                                    </td>
                                    <td>
                                        ${line.namaPemohon}
                                    </td>
                                    <td>
                                        <a onclick="deleteMohonTerdahulu(${line.permohonanManualSemasa.idMohonManual},this.form,'mohonManual')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a>
                                            <c:if test="${fn:length(actionBean.pmList) > 0}">
                                            <a onclick="updateMohonTerdahulu(${line.permohonanManualSemasa.idMohonManual},this.form,'mohonManual')" onmouseover="this.style.cursor='pointer';" ><img alt="Kemaskini" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
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
                        <s:button name="Tambah" id="save" value="Cari Permohonan Terdahulu" class="longbtn" onclick="addPermohonan()"/>
                        <s:button name="TambahNoFail" id="save" value="Tambah No Fail" class="btn" onclick="addFail()"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                    </td>
                </tr>
            </table>   
        </fieldset>
    </s:form>
</body>
