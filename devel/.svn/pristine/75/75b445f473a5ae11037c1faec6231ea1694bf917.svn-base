<%-- 
    Document   : maklumat_mesyuarat
    Created on : Nov 11, 2012, 10:25:53 AM
    Author     : Afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAKLUMAT KEPUTUSAN</title>
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
    }); //END OF READY FUNCTION
         
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshRekodKeputusanPindaanMMKV2('main');

            
        self.close();
    }
    
    function refreshpage2(){
        //        alert('aa');
        NoPrompt();
        opener.refreshRekodKeputusanPindaanMMKV2('beforeMesyuarat');

            
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
    <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanPindaanMMKV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="idKertas" id="idKertas" value="${actionBean.permohonanKertas.idKertas}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="keputusan_permohonan">
                    <legend>Maklumat Mesyuarat</legend>
                    <c:if test="${!actionBean.disRekodKeputusanController.sblmMesyuarat}">
                        <div class="content" align="center">
                            <table class="tablecloth" border="0">
                                <c:choose>
                                    <c:when test="${actionBean.kodNegeri eq '05'}">
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                            <tr>
                                                <td>Status Mesyuarat</td>
                                                <td>
                                                    <s:radio name="kpsnMohonFasa" value="L"/>Lulus&nbsp;
                                                    <s:radio name="kpsnMohonFasa" value="T"/>Tolak&nbsp;
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:when>
                                </c:choose>
                                <tr>
                                    <td>Nama Setiausaha MMK :</td>
                                    <td> <s:text name="permohonanKertas.penyediaSidang"/>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>Bilangan Mesyuarat :</td>
                                    <td> <s:text name="permohonanKertas.nomborRujukan"/>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>Tarikh Bersidang :</td>
                                    <td><s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>Tarikh Disahkan :</td>
                                    <td><s:text name="permohonanKertas.tarikhSahKeputusan" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                    </c:if>
                    <c:if test="${actionBean.disRekodKeputusanController.sblmMesyuarat}">
                        <div class="content" align="center">
                            <table class="tablecloth" border="0">
                                <tr>
                                    <td>Nama Setiausaha MMK :</td>
                                    <td> <s:text name="permohonanKertas.penyediaSidang"/>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>Bilangan Mesyuarat :</td>
                                    <td> <s:text name="permohonanKertas.nomborRujukan"/>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>Tarikh Bersidang :</td>
                                    <td><s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                    </c:if>

                </div>
            </fieldset>
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <c:if test="${!actionBean.disRekodKeputusanController.sblmMesyuarat}">
                        <tr>
                            <td align="center">
                                <s:submit name="simpanKeputusan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.disRekodKeputusanController.sblmMesyuarat}">
                        <tr>
                            <td align="center">
                                <s:submit name="simpanMesyuarat" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage2()"/>
                            </td>
                        </tr>
                    </c:if>
                </table>   
            </fieldset>         
        </div>
    </s:form>
</body>

