<%-- 
    Document   : Update_laporan_pelanV2AddFail
    Created on : jan 25, 2015, 8:33:14 PM
    Author     : Erra Zyra
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
    $(document).ready( function(){
        $(".datepicker").datepicker({
            dateFormat: 'dd/mm/yy',
            yearRange : '1950:2050'});

        if (document.getElementById("keputusan").checked == true) {
            if (document.getElementById("keputusan").checked == 'L') {
                alert("L");
                $('#note').show();
            }
            if (document.getElementById("keputusan").checked == 'T') {
                alert("T");
                $('#note').hide();
            }
        }
        if (document.getElementById("keputusan").checked == false) {
            $('#note').hide();
        }
    });

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
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?refreshpage&type='+type;
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
                $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?deleteRowMohonManual&idPihakPendeposit='+idMohonManual+'&tName='+tName,q,
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
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?showFormPopUp&idHakmilik="+idHakmilik+"&noPtSementara="+noPtSementara, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function openFrame(type){
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            var noPtSementara = $('#noPtSementara').val();
            window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?openFrame&idHakmilik="
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

        function show() {
            document.getElementById("note").style.visibility = 'visible';
            document.getElementById("note").style.display = '';
        <%--$("#note").show();--%>
            }

            function hide() {
                document.getElementById("note").style.visibility = 'hidden';
                document.getElementById("note").style.display = 'none';
        <%--$("#note").hide();--%>
            }
        
    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahV2PelupusanActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="kodD" id="kodD"/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.permohonanLaporanPelan.idLaporan}"/>
        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <s:hidden name="no_Fail" id="no_Fail" value="${actionBean.pm.noFail}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Permohonan Terdahulu</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                        <tr>
                            <td>No Fail :</td>
                            <td>${actionBean.pm.noFail}</td>
                        </tr>
                        <tr>
                            <td>Urusan :</td>
                            <td><s:select name="kodUrusan" id="kodLot" value="${actionBean.per.kodUrusan.kod}">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiUrusanPelupusan}" label="nama" value="kod" />
                                </s:select></td>
                        </tr>
                        <tr>
                            <td>Status Keputusan :</td>
                            <td>
                                <c:if test="${actionBean.per.keputusan.kod eq 'L'}">
                                    <input type="radio" checked="checked" name="keputusan" id="keputusan" value="L" onclick="show();"/>Lulus
                                    <input type="radio" name="keputusan" id="keputusan" value="T" onclick="hide();"/>Tolak
                                </c:if>
                                <c:if test="${actionBean.per.keputusan.kod eq 'T'}">
                                    <input type="radio" name="keputusan" id="keputusan" value="L" onclick="show();"/>Lulus
                                    <input type="radio" checked="checked" name="keputusan" id="keputusan" value="T" onclick="hide();"/>Tolak
                                </c:if>
                                <c:if test="${actionBean.per.keputusan.kod ne 'L' && actionBean.per.keputusan.kod ne 'T'}">
                                    <input type="radio" name="keputusan" id="keputusan" value="L" onclick="show();"/>Lulus
                                    <input type="radio" name="keputusan" id="keputusan" value="T" onclick="hide();"/>Tolak
                                </c:if>
                                <%--<s:radio name="keputusan" id="keputusan" checked="checked" value="L"/> Lulus &nbsp;
                                <s:radio name="keputusan" id="keputusan" value="T"/> Tolak</td>--%>
                            </td>
                        </tr>
                        <tr id="note">
                            <td>Catatan :</td>
                            <td>
                                <s:textarea name="note" id="catatan" cols="50" rows="4"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Keputusan Oleh :</td>
                            <td><s:text name="keputusanOleh" id="keputusanOleh" class="required"/></td>
                        </tr>
                        <tr>
                            <td>Tarikh Keputusan :</td>
                            <td><s:text name="tarikhKeputusan" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                            <%--<td style="color:black"><c:if test="${actionBean.prmhnn.tarikhKeputusan ne null}">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.prmhnn.tarikhKeputusan}"/>
                                </c:if>
                                <c:if test="${actionBean.prmhnn.tarikhKeputusan eq null}">Tiada Maklumat</c:if></td>--%>
                        </tr>
                        <tr>
                            <td>Pemohon :</td>
                            <td><s:text name="pmhn" id="pmhn" class="required"/></td>
                            <%--<td style="color:black"><c:if test="${actionBean.pemohon.pihak.nama ne null}">${actionBean.pemohon.pihak.nama} <br>
                                    ${actionBean.pemohon.pihak.noPengenalan} </c:if>
                                <c:if test="${actionBean.pemohon.pihak.nama eq null}">Tiada Maklumat</c:if></td>--%>
                        </tr>
                        <tr>
                            <td align="right" colspan="2">
                                <s:submit name="updateSimpanFail" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                <s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick=""/>
                                <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('pTanah')"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <br/>

            </fieldset>
        </div>
    </s:form>
</body>

