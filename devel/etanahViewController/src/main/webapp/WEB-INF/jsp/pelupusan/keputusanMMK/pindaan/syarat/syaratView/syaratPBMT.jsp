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
        <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.hakmilikPermohonan.id}"/>
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <table class="tablecloth" border="0">
                    <tr>
                        <td>
                            Luas Pelan B1 :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasPelanB1}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasPelanB1Uom.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Luas Diluluskan :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:center;" colspan="2">      
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>       
            </c:when>
            <c:when test="${actionBean.kodNegeri eq '05'}">
                <table class="tablecloth" border="0">
                    <tr>
                        <td>
                            Luas Pelan B1 :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasPelanB1}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.luasPelanB1Uom.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Luas Diluluskan :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasDiluluskan}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}

                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:center;" colspan="2">      
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>


    </s:form>