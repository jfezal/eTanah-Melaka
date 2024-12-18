
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">

    function cetak222(id1) {
alert(id1);
        var report = 'DEVSP_PA.rdf';
        var url = "reportName=" + report + "%26report_p_id_msp=" + id1;

        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url=" + url, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:messages />
<s:errors />

<s:form action="/utiliti/validateHakmilik" id="validateHakmilik" name="validateHakmilik">
    <fieldset class="aras1">
        <legend>Maklumat Pelan</legend>
        <div>
            <table cellpadding="0" cellspacing="0" align="center" class="tablecloth" datapagesize="20">

                <tr>
                    <th>ID HAKMILIK</th>
                    <th>JUMLAH LUAS</th>
                    <th>MAKLUMAT PENYERAH</th>
                    <th>CETAK</th>
                </tr> 
                <tr>
                    <td align="center"><ul style="list-style-type:circle">

                            <c:forEach items="${actionBean.idHakmilikL}" var="a">
                                <li>
                                    ${a}
                                </li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>${actionBean.jumlahLuas}</td>
                    <td>
                        <p>Nama:  ${actionBean.namaPenyerah}</p>

                        <p>No Telefon :  ${actionBean.noTel}</p>
                    </td>
                    <td>  
                        <s:button name="cetak" id="back" value="Cetak" class="btn" onclick="cetak222('${actionBean.idMsp}')"/>
                    </td>
                </tr> 
            </table>
        </div>
        &nbsp;

    </fieldset>

</s:form>
