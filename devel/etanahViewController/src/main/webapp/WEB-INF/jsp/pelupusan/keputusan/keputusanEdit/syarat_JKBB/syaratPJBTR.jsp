<%-- 
    Document   : syaratPJBTR
    Created on : Jan 09, 2013, 11:45:33 AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SYARAT-SYARAT</title>
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
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
    $(document).ready(function() {
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    <c:choose>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GM'}">
                $('#tPajakan').hide();
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM'}">
                $('#tPajakan').show();
        </c:when>
        <c:otherwise>
                $('#tPajakan').hide();
        </c:otherwise>
    </c:choose> 
        }); //END OF READY FUNCTION
         
        function refreshpage(){
            //        alert('aa');
            NoPrompt();
            opener.refreshRekodKeputusanV2('main');

            
            self.close();
        }
        function cariPopup(val){
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKBB?searchSyarat&jenisSyarat="+val, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        function showTempoh(val){
            if(val == 'PM')
                $('#tPajakan').show();
            else 
                $('#tPajakan').hide();
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
    <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanJKBBActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="keputusan_permohonan">
                    <legend>Syarat-syarat Kelulusan</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>
                                    Luas Disyorkan :
                                </td>
                                <td>
                                    ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.luasLulusUom.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Luas Diluluskan :
                                </td>
                                <td>
                                    <s:text name="hakmilikPermohonan.luasDiluluskan"/> 
                                    <s:select name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.luasLulusUom.kod}">
                                        <s:option value="M">Meter Persegi</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <%-- <tr>
                                 <td>
                                     Jenis Hakmilik
                                 </td>
                                 <td>
                                     <s:select name="kodHakmilik" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}" onclick="showTempoh(this.value);"  style="width:150px" id="kodHakmilik">
                                         <s:option value="">Sila Pilih</s:option>
                                         <s:option value="GM">Geran Mukim</s:option>
                                         <s:option value="PM">Pajakan Mukim</s:option>
                                     </s:select>
                                 </td>
                             </tr> --%>
                            <tr>
                                <td>
                                    Tempoh Pajakan
                                </td>
                                <td>
                                    <s:text name="hakmilikPermohonan.tempohPajakan"/> Tahun
                                </td>
                            </tr>
                            <tr>
                                <td><font color="red" size="4">*</font>Bayaran (RM) :</td>
                                <td>
                                    <s:text name="amnt" size="20" onkeyup="validateNumber(this,this.value);"/> &nbsp; Setahun
                                </td>
                            </tr>
                            <%--     <tr>
                                    <td>
                                        Kegunaan Tanah
                                    </td>
                                    <td>
                                        <s:select name="kodGunaTanah"  value="${actionBean.hakmilikPermohonan.kodKegunaanTanah.kod}"style="width:210px;" id="kodKegunaTanah">
                                            <s:option value="">-- Sila Pilih --</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" value="kod" label="nama" />
                                        </s:select>
                                    </td>
                                </tr>
                               
                                <tr>
                                    <td>
                                        Syarat Nyata Diluluskan
                                    </td>
                                    <td>
                                        <s:textarea name="hakmilikPermohonan.syaratNyataBaru.syarat" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                                        <s:hidden name="disSyaratSekatan.kod" id="kod"/>                                    
                                    </td>
                                    <td style="vertical-align: middle;">
                                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('nyata')"/>  
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Sekatan Kepentingan Diluluskan
                                    </td>
                                    <td>
                                        <s:textarea name="hakmilikPermohonan.sekatanKepentinganBaru.sekatan" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                                        <s:hidden name="disSyaratSekatan.kodSktn" id="kodSktn"/>

                                </td>
                                <td style="vertical-align: middle;">
                                    <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('sekatan')"/>
                                </td>
                            </tr>--%>
                        </table>
                    </div>
                </div>
            </fieldset>
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:submit name="simpanSyarat" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>    
        </div>
    </s:form>
</body>