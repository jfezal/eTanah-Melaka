<%-- 
    Document   : laporan_tanahV2TambahKawasan.jsp
    Created on : 07 March, 2012, 3:08:57 PM
    Author     : Rohan
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<title>UTILITI DALAM KAWASAN</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker-ms.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
   
    function validate()
    {
        a = document.test.id ;
  
        if (document.test.id.value==null||document.test.id.value=="")
        {

            alert("Sila masukkan id permohonan");
            a.focus()
            return false;
        }
        else
        {
     
            return true ;
        }
  
    }

    function test1(){
    
        self.close();

    
    
    
    
    
  
    }
    function openLain_lain(val){
        if(val == '99'){
            $('#catatanKwsn').show();
        }else{
            $('#catatanKwsn').hide();
        }
    }
    function save1(event, f){
          
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            //self.opener.refreshRizab();
            opener.refreshlptn();
            self.close();
        },'html');
            
    }
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'})
    <c:if test="${actionBean.disLaporTanahKawasan.stringRizab eq null}">
            $('#catatanKwsn').hide();
    </c:if>
    <c:if test="${actionBean.disLaporTanahKawasan.stringRizab ne null and actionBean.disLaporTanahKawasan.stringRizab eq '99'}">
            $('#catatanKwsn').show();
    </c:if>
        });
    
        function openFrame(type){
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
            //    alert(idHakmilik);
            window.open("${pageContext.request.contextPath}/pengambilan/laporan_tanahV2?openFrame&idHakmilik="
                +idHakmilik+"&type="+type, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
            //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
        }
        function refreshpage(){
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
    <s:form beanclass="etanah.view.stripes.pengambilan.LaporanTanahNewDisposalActionBean" name="test">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>Tanah Dipohon Berada Dalam Kawasan :</legend>
                    <s:hidden name="id2" value="id"/>
                    <table class="tablecloth" border="0" align="center">
                        <tr>
                            <td align="left">Jenis Rizab :</td>
                            <td style="color:black">:</td>
                            <td align="left" style="color:black">
                                <s:select name="disLaporTanahKawasan.stringRizab" onchange="openLain_lain(this.value)">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod" />
                                </s:select>
                            </td>
                        </tr>
                        <tr id="catatanKwsn">
                            <td align="left">Catatan</td>
                            <td style="color:black">:</td>
                            <td align="left" style="color:black">
                                <s:textarea name="disLaporTanahKawasan.catatan" rows="5" cols="50" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left">No Warta</td>
                            <td style="color:black">:</td>
                            <td align="left" style="color:black">
                                <s:text name="disLaporTanahKawasan.noWarta" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left">Tarikh Warta</td>
                            <td style="color:black">:</td>
                            <td align="left" style="color:black">
                                <s:text name="disLaporTanahKawasan.tarikhWarta" size="12" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/> <em>[hh/bb/tttt]</em>
                            </td>
                        </tr>
                        <tr>
                            <td align="left">No Pelan Warta</td>
                            <td style="color:black">:</td>
                            <td align="left" style="color:black">
                                <s:text name="disLaporTanahKawasan.pelanWarta" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td align="left" colspan="3">
                                <s:submit name="simpanKawasan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                <%--<s:button name="simpanKawasan" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>--%>
                                <s:button name="tutup" value="Kembali" class="btn" onclick="openFrame('dKawasan')"/>                
                            </td>
                        </tr>
                    </table>
                </div>

            </fieldset>
        </div>
    </s:form>
</body>

