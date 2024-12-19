<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
    
    $(document).ready(function() {
        $('#refresh').hide();
    });
    
    function save(event, f) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function (data) {
                    $('#page_div').html(data);
                    $.unblockUI();
                }, 'html');
    }

    function deletePelan(val, f) {
        if (confirm('Adakah pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonanNew?deletePelanTamb&idAksr=' + val;
            var q = $(f).formSerialize();
            $.post(url, q,
                    function (data) {
                        $('#page_div').html(data);
                    }, 'html');
                    $('#refresh').show();
                    alert("Sila tekan butang 'Refresh' untuk menyemak senarai no pelan terbaru.");
        }
//        refreshPage();
    }

    function refreshPage() {
        var url = '${pageContext.request.contextPath}/strata/maklumat_hakmilik_permohonanNew?addPelan';
        $.get(url,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.strata.NewStrataMaklumatHakmilikPermohonanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">  
            <br />
            <br />
            <p>
                <label>Bilangan No Pelan Tambahan (per Hakmilik)</label>
                <s:text name="bil" id="bil" />&nbsp;
                <s:submit name="addPelan" value="Kemaskini"  class="btn"/>
            </p>  
            <br/>
            <c:if test="${!empty actionBean.bil}" >
                <br />
                <p>
                    <label>Id Hakmilik</label>
                    <s:text name="idHakmilikPelan" id="idHakmilikPelan" size="35"/>
                </p> 
                <br />
            </c:if>
            <center>
                <table border=0 class="tablecloth"><tr>
                        <c:forEach var="i" begin="1" end="${actionBean.bil}" >
                            <th align=right>
                                No Pelan ${i}:
                            </th>
                            <td align=left>

                                PA(B)<s:text id="noPelanTamb${i - 1}" name="noPelanTamb${i - 1}"  size="25"
                                        onkeyup="this.value = this.value.toUpperCase();"  />&nbsp;

                            </td>
                            <c:if test="${i % 2 == 0}" >
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
                <c:if test="${!empty actionBean.bil}" >
                    <br />
                    <s:submit class="btn" name="simpanPelanTamb" value="Simpan" onclick="save(this.name, this.form);"/>
                </c:if>
                <%--<s:submit name="semakPelan" value="Semak Pelan"  class="btn"/>--%>
                <br />
                <br />
                <%--<c:if test="${!empty actionBean.listakmilikPetakAksesori}" >--%>
                <s:submit name="addPelan" value="Refresh" id="refresh" class="btn"/>
                <display:table style="width:80%" class="tablecloth" name="${actionBean.listakmilikPetakAksesori}" id="line">
                    <display:column title="Id Hakmilik" property="hakmilik.idHakmilik"/>
                    <display:column title="No Pelan" property="noPelan"/>
                    <display:column title="Tindakan" >
                        <center> 
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                 onclick="deletePelan('${line.idAksesori}', this.form);" onmouseover="this.style.cursor = 'pointer';">
                        </center>
                    </display:column>
                </display:table>
                <%--</c:if>--%>
            </center>
        </fieldset>
    </div>
</s:form>



