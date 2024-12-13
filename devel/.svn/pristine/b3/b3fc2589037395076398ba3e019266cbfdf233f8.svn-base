<%-- 
    Document   : laporan_pelanV2AddPermohonanTerdahulu
    Created on : Mar 4, 2013, 10:33:14 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tambah Permohonan Terdahulu</title>
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
        
        function deleteRowMohonManual(idMohonManual,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?deleteRowMohonManual&idPihakPendeposit='+idMohonManual+'&tName='+tName,q,
                function(data){
                    $('#page_div').html(data);
                    
                }, 'html');
                
                self.refreshpage2('pendeposit') ;
            }
        }
        
        function addPermohonan(){
    <%--alert(id);--%>
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
            //            alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?showFormPopUp&idHakmilik="+idHakmilik+"&noPtSementara="+noPtSementara, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function openFrame(type){
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_pelanV2?openFrame&idHakmilik="
                +idHakmilik+"&noPtSementara="+noPtSementara+"&type="+type, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
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
                            <td>Id Permohonan :</td>
                            <td><s:text name="id" id="id" class="required"/></td>
                        </tr>
                        <tr>
                            <td align="right" colspan="2">
                                <s:submit name="cariPermohonan" value="Cari" class="btn" onclick="NoPrompt();"/>
                                <s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick=""/>
                                <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('pTerdahulu')"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>
        <div class="subtitle">
            <c:if test="${status}">
                <fieldset class="aras1">
                    <legend>Status Permohonan ( ${actionBean.prmhnn.kodUrusan.nama} )</legend>
                    <div class="content" align="center">
                        <s:hidden name="id2" value="id"/>
                        <table class="tablecloth" border="0">

                            <tr>
                                <td>Status Keputusan</td>
                                <td>:</td>
                                <td style="color:black"><c:if test="${actionBean.prmhnn.keputusan.nama ne null}">${actionBean.prmhnn.keputusan.nama}</c:if>
                                    <c:if test="${actionBean.prmhnn.keputusan.nama eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td>Tarikh Keputusan</td>
                                <td style="color:black">:</td>
                                <td style="color:black"><c:if test="${actionBean.prmhnn.tarikhKeputusan ne null}">
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.prmhnn.tarikhKeputusan}"/>
                                    </c:if>
                                    <c:if test="${actionBean.prmhnn.tarikhKeputusan eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td>Keputusan Oleh</td>
                                <td style="color:black">:</td>
                                <td style="color:black"><c:if test="${actionBean.prmhnn.keputusanOleh.nama ne null}">${actionBean.prmhnn.keputusanOleh.nama}</c:if>
                                    <c:if test="${actionBean.prmhnn.keputusanOleh.nama eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td>Pemohon</td>
                                <td style="color:black">:</td>
                                <td style="color:black"><c:if test="${actionBean.pemohon.pihak.nama ne null}">${actionBean.pemohon.pihak.nama} <br>
                                        ${actionBean.pemohon.pihak.noPengenalan} </c:if>
                                    <c:if test="${actionBean.pemohon.pihak.nama eq null}">Tiada Maklumat</c:if></td>
                            </tr>
                            <tr>
                                <td align="right" colspan="3">
                                    <c:if test="${!simpan}">
                                        <s:submit name="simpanPermohonanSblm" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                    </c:if>
                                    <s:button name="cariSemula" value="Cari Semula" class="btn" onclick="addPermohonan();"/>    
                                </td>
                            </tr>
                        </table> 
                    </div>
                </fieldset>

            </c:if>
        </div>
    </s:form>
</body>

