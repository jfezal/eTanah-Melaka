<%-- 
    Document   : popup_view_saksi
    Created on : April 26, 2011, 06:43:51 PM
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

<s:form  beanclass="etanah.view.penguatkuasaan.KeteranganSaksiActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Saksi
            </legend>
            <div class="content">
                <p>
                    <label>Nama :</label>
                    ${actionBean.permohonanSaksi.nama}
                </p>
                <p>
                    <label>Jenis Pengenalan :</label>
                    ${actionBean.permohonanSaksi.jenisPengenalan.nama}
                </p>
                <p>
                    <label>No.Pengenalan :</label>
                    ${actionBean.permohonanSaksi.noPengenalan}
                </p>
                <p>
                    <label>Alamat :</label>
                    ${actionBean.permohonanSaksi.alamat1}&nbsp;
                    ${actionBean.permohonanSaksi.alamat2}&nbsp;
                    ${actionBean.permohonanSaksi.alamat3}&nbsp;
                    ${actionBean.permohonanSaksi.alamat4}&nbsp;
                    ${actionBean.permohonanSaksi.poskod}&nbsp;
                    ${actionBean.permohonanSaksi.negeri.nama}&nbsp;

                </p>
                <p>
                    <label>No. Telefon :</label>
                    ${actionBean.permohonanSaksi.noTelefon}
                </p>
                <p>
                    <label>Email :</label>
                    ${actionBean.permohonanSaksi.email}
                </p>
                <p>
                    <label>Pekerjaan :</label>
                    ${actionBean.permohonanSaksi.pekerjaan}
                </p>
                <br/>
                <br/>
                <p align="center">
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>