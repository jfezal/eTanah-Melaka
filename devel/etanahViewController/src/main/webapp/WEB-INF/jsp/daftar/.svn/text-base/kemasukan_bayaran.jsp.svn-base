
<%--
    Document   : kemasukan_perincian_hakmilik
    Created on : 21 Oktober 2009, 4:07:08 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
    <c:if test="${actionBean.p.kodUrusan.kod eq 'HSP' && actionBean.p.kodUrusan.kod eq 'HKP'}">
        <c:set var="disabled" value=""/>
    </c:if>

    <c:if test="${actionBean.p.kodUrusan.kod ne 'HSP' && actionBean.p.kodUrusan.kod ne 'HKP'}">
        <c:set var="disabled" value="disabled"/>
    </c:if>
    <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
    <%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>

    <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS'}">
      <c:set var="disabledbtn" value="disabled"/>
    </c:if>--%>
    <script type="text/javascript">





        function simpanBayaran2(event, f) {
            var id = $('#hiddenIdHakmilik').val();
            var bayar = $("#bayaran").val();
            var url = '${pageContext.request.contextPath}/pendaftaran/KemasukanSubMC?SimpanBayaran&bayar=' + bayar;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    });
        }

    </script>

    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.KemasukanSubMC" id="kemasukanPerincianHakmilik">
        <s:messages/>
        <s:errors/>
        <fieldset class="aras1">
            <legend>Tentukan Bayaran Pelan Khas</legend>
            <%--${disablebtn}--%>
            <p><label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}
                &nbsp;
            </p>

            <p><label>Bayaran RM:</label>
                <s:text name="bayaran" id="bayaran" size="10" style="text-transform:uppercase"/>
                &nbsp;
            </p>
            <br>
            <%--<s:hidden name="hakmilik.noHakmilik" value="${actionBean.hakmilik.noHakmilik}" />--%>
            <center>
                <%--<s:button name="SimpanBayaran"  id="SimpanBayaran"  value="Simpan" class="btn" onclick="doSubmit(this.name, this.form);" />--%>
                <s:button name="SimpanBayaran" id="SimpanBayaran" value="Simpan" class="btn" onclick="simpanBayaran2(this.form);"/>&nbsp;
            </center>

        </fieldset>


    </s:form>
</body>