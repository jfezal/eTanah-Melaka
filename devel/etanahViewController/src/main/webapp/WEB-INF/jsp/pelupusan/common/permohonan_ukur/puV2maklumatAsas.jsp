<%--
    Document   :  puV2maklumatAsas.jsp
    Created on :  November 13, 2012, 01:10:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAKLUMAT ASAS</title>
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
   
    $(document).ready(function() {
         
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        
    }); //END OF READY FUNCTION
             
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
            
    function refreshpage(){
            //            alert('aa');
            NoPrompt();
            opener.refreshV2('main');
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
    <s:form beanclass="etanah.view.stripes.pelupusan.common.PUV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">

                <div id="perihaltanah">
                    <legend>Maklumat Asas</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>Rujukan Pejabat Tanah</td>
                                <td>:</td>
                                <td>
                                    ${actionBean.idPermohonan}
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Rujukan Pejabat Ukur
                                </td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.noRujukanPejabatUkur" size="30" id="rujukan" disabled="false"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Negeri
                                </td>
                                <td>:</td>
                                <td>
                                    <c:if test="${actionBean.kodNegeri eq '04'}">
                                        Melaka
                                    </c:if>
                                    <c:if test="${actionBean.kodNegeri eq '05'}">
                                        Negeri Sembilan
                                    </c:if>
                                    
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    No Permohonan Ukur
                                </td>
                                <td>:</td>
                                <td>
                                    <c:if test="${actionBean.permohonanUkur.noPermohonanUkur eq null}">
                                        <font color="red">Sila Klik butang Simpan dan Jana No PU untuk Menjana No PU &nbsp;</font>
                                    </c:if>
                                      <c:if test="${actionBean.permohonanUkur.noPermohonanUkur ne null}">
                                         ${actionBean.permohonanUkur.noPermohonanUkur}  
                                    </c:if>                                     
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Tarikh Permohonan Ukur
                                </td>
                                <td>:</td>
                                <td>
                                    <c:if test="${actionBean.permohonanUkur.noPermohonanUkur eq null}">
                                        <font color="red">Sila Klik butang Simpan dan Jana No PU untuk Menjana Tarikh Permohonan Ukur&nbsp;</font>
                                    </c:if>
                                      <c:if test="${actionBean.permohonanUkur.noPermohonanUkur ne null}">
                                        <fmt:formatDate value="${actionBean.permohonanUkur.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"  />
                                    </c:if>                                     
                                </td>
                            </tr>
                        </table>        
                    </div>
                    <br/>
                </div>
            </fieldset>

            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:submit name="simpanMaklumatAsas" value="Simpan & Jana No PU" class="longbtn" onclick="NoPrompt();"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>
    </s:form>
</body>

