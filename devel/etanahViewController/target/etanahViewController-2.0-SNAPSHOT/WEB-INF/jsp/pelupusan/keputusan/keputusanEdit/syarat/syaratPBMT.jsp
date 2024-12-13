<%-- 
    Document   : syaratPBMT
    Created on : Mar 13, 2013, 4:09:44 PM
    Author     : afham
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
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'GRN'}">
                $('#tPajakan').hide();
        </c:when>
        <c:when test="${actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PM' || actionBean.hakmilikPermohonan.kodHakmilik.kod eq 'PN'}">
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
    
    function doSomething(val){
          
            if(val=='GRN'|| val=='GM'){
                //alert(val);
                $('#jikaPajakan').hide();
            }else if (val=='PN' || val=='PM'){
                //alert(val);
                $('#jikaPajakan').show();
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
    <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanV2ActionBean" name="form">
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
                                    <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Luas Diluluskan :
                                </td>
                                <td>
                                    <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000"/> 
                                    ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                                    <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>

                                </td>
                            </tr>
                            <tr>
                                    <td><font color="red" size="4">*</font>Kategori</td>
                                    <td>
                                        ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}
                                        <s:hidden name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}"/>
                                       <!-- <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="listkegunaantanah(this.form);"> 
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                                        </s:select>-->
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Kegunaan Tanah</td>
                                    <td>
                                        <s:select name="kodGunaTanah"  value="${actionBean.hakmilikPermohonan.kodKegunaanTanah.kod}" style="width:300px;" id="_kodKegunaTanah">
                                        <s:option value="">-- Sila Pilih --</s:option>
                                        <s:options-collection collection="${actionBean.senaraiKodKegunaanTanah}" value="kod" label="nama" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Jenis Hakmilik :</td>
                                    <td>
                                        <s:select name="kodHmlk" id="kodHmlk" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}" onchange="doSomething(this.value);" >
                                            <s:option value="0">--Sila Pilih--</s:option>
                                            <s:option value="GRN">Geran (Pendaftar)</s:option>
                                            <s:option value="PN">Pajakan Negeri (Pendaftar)</s:option> 
                                            <s:option value="GM">Geran Mukim (Pejabat Tanah)</s:option>
                                            <s:option value="PM">Pajakan Mukim (Pejabat Tanah)</s:option>
                                        </s:select>
                                    </td>
                                </tr>
                                <tr id="jikaPajakan">
                                        <td><font color="red" size="4">*</font>Tempoh Pajakan:</td>
                                        <td>
                                            <s:select name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan">
                                                <s:option value="0">--Sila Pilih--</s:option>
                                                <s:option value="30"> 30 </s:option>
                                                <s:option value="60"> 60 </s:option>
                                                <s:option value="99"> 99 </s:option>
                                            </s:select>
                                        </td>
                                </tr>                              
                                <tr>
                                    <td><font color="red" size="4">*</font>Premium : </td>
                                    <td>
                                        <s:select name="keteranganKadarPremium" value="${actionBean.hakmilikPermohonan.keteranganKadarPremium}" id="nama" onchange="javaScript:showPremimTxt(this.value)">
                                            <s:option value="">--Sila Pilih--</s:option>
                                            <s:options-collection collection="${actionBean.senaraikodKadarPremium}" />
                                        </s:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td><font color="red" size="4">*</font>Hasil (RM) :</td>
                                    <td>
                                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="10"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <font color="red" size="4">*</font>Upah Ukur :
                                    </td>
                                    <td>
                                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;Juru Ukur Berlesen
                                    </td>
                                </tr>
                            
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