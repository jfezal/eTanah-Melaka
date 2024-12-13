<%-- 
    Document   : popup_view_mahkamah
    Created on : July 15, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.penguatkuasaan.MahkamahActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Mahkamah
            </legend>
            <div class="content">
                <p>
                    <label>Kategori Mahkamah :</label>
                    ${actionBean.permohonanRujukanLuar.agensi.nama}
                    &nbsp;

                </p>
                <p>
                    <label>Tempat Mahkamah :</label>
                    ${actionBean.permohonanRujukanLuar.namaSidang}&nbsp;
                </p>
                <p>
                    <label>No Rujukan :</label>
                    ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
                </p>
                <p>
                    <label>Tarikh Sebutan/Perbicaraan :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhSidang}"/>&nbsp;

                </p>
                <p>
                    <label>Tarikh Keputusan :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanRujukanLuar.tarikhRujukan}"/>&nbsp;
                </p>
                 <p>
                    <label>Status :</label>
                    ${actionBean.permohonanRujukanLuar.keputusanPendakwaan.nama}
                    &nbsp;

                </p>
                <p>
                    <label>Minit :</label>
                    ${actionBean.permohonanRujukanLuar.catatan}&nbsp;
                </p>


                <br/>
            </div>

        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Mahkamah
            </legend>
            <br/>

            <div class="content">

                <fieldset class="aras2">
                    <legend>
                        Pihak Mahkamah
                    </legend>

                    <div align="center" >
                        <display:table name="${actionBean.senaraipermohonanRujukanLuarPeranan}" id="line" class="tablecloth" >
                            <display:column title="Bil">
                                ${line_rowNum}
                            </display:column>
                            <display:column property="nama" title="Nama" />
                            <display:column title="Peranan" property="kodPerananRujukanLuar.nama"/>
                        </display:table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <br/>

            </div>
        </fieldset>
    </div>
    <p><label>&nbsp;</label>
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
    </p>
</s:form>

