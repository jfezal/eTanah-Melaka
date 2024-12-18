<%-- 
    Document   : jualan_pelan_info
    Created on : Jun 9, 2010, 10:56:53 AM
    Author     : abdul.hakim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:30em;
    }
</style>

<s:form beanclass="etanah.view.stripes.hasil.JualanPelanActionBean" id="jualan_pelan">

    <fieldset class="aras1">
            <legend>Maklumat Pelan</legend>
            <p>
                <label>Negeri :</label>
                MELAKA
            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.daerah}&nbsp;
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                ${actionBean.bpm}&nbsp;
            </p>
            <p>
                <label>Jenis Carian :</label>
                ${actionBean.jenisCarian}&nbsp;
            </p>
            <p>
                <label>No. Carian :</label>
                ${actionBean.noCarian}&nbsp;
            </p>
            <%--<p>
                <label>No. PT :</label>
                &nbsp;
            </p>--%>
            <p>
                <label>Bilangan Lot-lot Bersebelahan :</label>
                ${actionBean.bilLot}&nbsp;
            </p>
            <p>
                <label>Lot-Lot Bersebelahan :</label>
                <table>
                    <c:forEach items="${actionBean.senaraiLotSebelah}" var="senarai">
                        <tr>
                            <td id="tdDisplay"><c:out value="${senarai}"/></td>
                        </tr>
                    </c:forEach>
                </table>&nbsp;
            </p>
            <p>
                <label>&nbsp;</label>
                &nbsp;
            </p>
        </fieldset>
        <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <s:button name=" " value="Tutup" class="btn" onclick="javaScript:window.close();"/>
                </td>
            </tr>
        </table></div>
</s:form>