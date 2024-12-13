<%-- 
    Document   : laporan_pelanV2AddDalamKawasan
    Created on : Mar 8, 2013, 4:46:22 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tambah Dalam Kawasan</title>
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
    $(document).ready(function() {
    $('#catatanKwsn').hide(),$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
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
        
        function openLain_lain(val){
            if(val == '99'){
                $('#catatanKwsn').show();
            }else{
                $('#catatanKwsn').hide();
            }
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
        <s:hidden name="idtanahrizabPermohonan" id="idtanahrizabPermohonan"/>
        <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Tanah Dipohon Berada Dalam Kawasan</legend>
                    <div class="content" align="center">
                        <s:hidden name="id2" value="id"/>
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>
                                    <font color="red" size="4">*</font>Jenis Rizab :
                                </td>
                                <td>
                                    <s:select name="kodRizab" onchange="openLain_lain(this.value)" value="${actionBean.permohonanLaporanKWS.kodRizab.kod}">
                                        <s:option value="0">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod" />
                                    </s:select>

                                </td>
                            </tr>
                            <tr id="catatanKwsn">
                                <td><font color="red" size="4">*</font>Catatan :</td>
                                <td>
                                    <%--<s:text name="catatanKws" value="${actionBean.permohonanLaporanKWS.catatan}" size="30"/>--%>
                                    <s:text name="catatan" value="${actionBean.permohonanLaporanKWS.catatan}" size="30"/>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No Warta :</td>
                                <td>
                                    <s:text name="addnoWarta" value="${actionBean.permohonanLaporanKWS.noWarta}" size="30" />
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Tarikh Warta :</td>
                                <td>
                                    <s:text name="addtarikhWarta" value="${actionBean.permohonanLaporanKWS.tarikhWarta}" size="12" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/> <em>hh/bb/tttt</em>
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>No Pelan Warta :</td>
                                <td>
                                    <s:text name="addnoPelanWarta" value="${actionBean.permohonanLaporanKWS.noPelanWarta}" size="30" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <s:submit name="simpanKawasan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                    <s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick=""/>
                                    <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('dKawasan')"/>            
                                </td>
                            </tr>

                        </table> 
                    </div>
                </fieldset>
        </div>
    </s:form>

</body>
