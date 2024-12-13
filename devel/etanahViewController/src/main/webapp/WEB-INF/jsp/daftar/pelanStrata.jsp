
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

    <c:set var="disabled" value=""/>



    <c:set var="disabled" value="disabled"/>

    <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
    <%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>

    <%--<c:if test="${actionBean.p.kodUrusan.kod eq 'HKABS'}">
      <c:set var="disabledbtn" value="disabled"/>
    </c:if>--%>
    <script type="text/javascript">


        function checkPelan2(event, f) {
            var id = $('#hiddenIdHakmilik').val();
            var pelan = $("#pelan1").val();
//            alert("bayar = " + pelan);
            var url = '${pageContext.request.contextPath}/strata/pelan?simpanPelan&pelan=' + pelan;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    });
        }


        function simpanBayaran2(event, f) {
            var id = $('#hiddenIdHakmilik').val();
            var bayar = $("#pelan1").val();
//            alert("bayar = " + bayar);
            var url = '${pageContext.request.contextPath}/pendaftaran/KemasukanSubMC?save&bayar=' + bayar;
            $.get(url,
                    function (data) {
                        $('#page_div').html(data);
                    });
        }

    </script>

    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.strata.PelanStrata" id="pelanStrata">
        <s:messages/>
        <s:errors/>
        <fieldset class="aras1">
            <c:if test="${jupem}">
                <label><font color="red">Sila Jana Surat Permohonan kepada JUPEM1</font></label>
                    </c:if>
                    <c:if test="${pelan}">
                <label>Sila Muat Naik Pelan PA(B)</label>${actionBean.senaraiPelanString}
                <s:file name="file" id="pelan1" accept="image/tif"/>
                <em>Pastikan nama fail adalah nama pelan cth:1234-01111</em>
                <br><br>
                <label>&nbsp;</label>
                <s:button name="simpanPelan" value="Simpan Pelan" class="longbtn" onclick="checkPelan2(this.name, this.form);"/>
                <%--<s:button name="save" id="save" value="Simpan" class="btn" onclick="checkPelan2(this.name, this.form);"/>&nbsp;--%>
                <%--<s:submit name="save" value="Simpan2" class="btn" onclick="simpan('${actionBean.permohonan.idPermohonan}');"/>--%>
                <s:hidden name="file" id="file"  value="${actionBean.file}" />
            </c:if>
        </fieldset>
        <br>
        <fieldset class="aras1">
            <p><label><em>*</em>Senarai Pelan Belum Dimuat Naik : </label>
                <display:table class="tablecloth" name="${actionBean.senaraiPelanUpload}" cellpadding="0" cellspacing="0"
                               requestURI="/strata/pelan" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column title="Nombor Lot/PT" style="vertical-align:baseline">
                        ${(actionBean.senaraiPelanUpload[line_rowNum-1])} 
                    </display:column>
                </display:table>
            </p>

        </fieldset>


    </s:form>
</body>