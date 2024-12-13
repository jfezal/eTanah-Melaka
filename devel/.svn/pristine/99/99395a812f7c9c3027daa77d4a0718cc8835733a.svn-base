<%-- 
    Document   : maklumat_mesyuarat JKBB
    Created on : Jan 09, 2013, 11:41:53 AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAKLUMAT KEPUTUSAN JKBB</title>
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
        opener.refreshRekodKeputusanV2('main');

            
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
    <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanJKBBActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="idKertas" id="idKertas" value="${actionBean.permohonanKertas.idKertas}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="keputusan_permohonan">
                    <legend>Maklumat Mesyuarat</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                                    <c:if test="${actionBean.stageId eq 'semak_masuk_bil_kertas_JKBB'}">
                                        <tr>
                                            <td>Bilangan Mesyuarat :</td>
                                            <td> <s:text name="permohonanKertas.nomborRujukan"/>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>Keputusan :</td>
                                            <td><s:radio name="keputusan" value="L" disabled="true"/>Lulus&nbsp;
                                                <s:radio name="keputusan" value="T" disabled="true"/>Tolak&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>Tarikh Bersidang :</td>
                                            <td><s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" disabled="true"/>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>Tarikh Disahkan :</td>
                                            <td><s:text name="permohonanKertas.tarikhSahKeputusan" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy" disabled="true"/>&nbsp;</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.stageId ne 'semak_masuk_bil_kertas_JKBB'}">
                                        <tr>
                                            <td>Bilangan Mesyuarat :</td>
                                            <td> <s:text name="permohonanKertas.nomborRujukan"/>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>Keputusan :</td>
                                            <td><s:radio name="keputusan" value="L"/>Lulus&nbsp;
                                                <s:radio name="keputusan" value="T"/>Tolak&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>Tarikh Bersidang :</td>
                                            <td><s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td>Tarikh Disahkan :</td>
                                            <td><s:text name="permohonanKertas.tarikhSahKeputusan" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;</td>
                                        </tr>
                                    </c:if>    
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>Bilangan Mesyuarat :</td>
                                        <td> <s:text name="permohonanKertas.nomborRujukan"/>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>Keputusan :</td>
                                        <td><s:radio name="keputusan" value="L"/>Lulus&nbsp;
                                            <s:radio name="keputusan" value="T"/>Tolak&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>Tarikh Bersidang :</td>
                                        <td><s:text name="permohonanKertas.tarikhSidang" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>Tarikh Disahkan :</td>
                                        <td><s:text name="permohonanKertas.tarikhSahKeputusan" id="datepicker1" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>

                        </table>
                    </div>
                </div>
            </fieldset>
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:submit name="simpanKeputusan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>         
        </div>
    </s:form>
</body>

