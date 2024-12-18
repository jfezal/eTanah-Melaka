<%--
    Document   : popup_view_syor_kompaun
    Created on : May 20, 2011, 11:52:22 AM
    Author     : latifah.iskak
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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
<script type="text/javascript">
    $(document).ready(function() {
        var a = 0.00;
    <c:forEach items="${actionBean.listItemBarang}" var="line">
            if(${line.amaunKompaunSyor !=""}){
                a += parseFloat(${line.amaunKompaunSyor});
                document.getElementById('jumCaraBayar').value=a+".00";
            }
    </c:forEach>

        });
 
</script>

<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatKompaunActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tawaran Kompaun
            </legend>
            <div class="content">
                <p>
                    <label>No.Siri Kompaun :</label>
                    ${actionBean.kompaun.noRujukan}&nbsp;
                </p>
                <p>
                    <label>Tempoh (Hari) :</label>
                    ${actionBean.kompaun.tempohHari}&nbsp;
                </p>
                <p>
                    <label>Isu Kepada :</label>
                    ${actionBean.kompaun.isuKepada}&nbsp;
                </p>
                <p>
                <fieldset class="aras2">
                    <legend>
                        Barang Rampasan
                    </legend>
                    <div class="instr-fieldset">
                        Item barang rampasan yang akan dikenakan kompaun
                    </div>

                    <div align="center" >
                        <display:table class="tablecloth" name="${actionBean.listItemBarang}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Barang Rampasan"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></display:column>
                            <display:column title="Kuantiti">${line.kuantiti} ${line.kuantitiUnit}</display:column>
                            <display:column title="Tarikh Rampasan" class="${line_rowNum}">
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhRampas}"/> <br>
                            </display:column>
                            <display:column title="Syor Kompaun (RM)" property="amaunKompaunSyor" format="{0,number, 0.00}"></display:column>
                            <display:footer>
                                <tr>
                                    <td colspan="4" align="right">Jumlah Syor(RM):</td>
                                    <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                                               class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                <tr>
                                </display:footer>

                            </display:table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <p align="center">
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>