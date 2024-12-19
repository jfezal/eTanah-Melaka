<%-- 
    Document   : view_waris_detail
    Created on : Aug 5, 2011, 11:35:10 PM
    Author     : siti zainal
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>

<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatWarisActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Waris
            </legend>

            <div class="content">
                <p>
                    <label>Nama Waris :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.nama}&nbsp;
                </p>

                <p>
                    <label>Jenis Pengenalan :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.jenisPengenalan.nama}&nbsp;
                </p>

                <p>
                    <label>No Pengenalan :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.noPengenalan}&nbsp;
                </p>

                <p>
                    <label>Alamat :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.alamat1}&nbsp;
                </p>

                <p>
                    <label>&nbsp</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.alamat2}&nbsp;
                </p>

                <p>
                    <label>&nbsp</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.alamat3}&nbsp;
                </p>

                <p>
                    <label>&nbsp</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.alamat4}&nbsp;
                </p>

                <p>
                    <label>&nbsp</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.poskod}&nbsp;
                </p>

                <p>
                    <label>Negeri :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.negeri.nama}&nbsp;
                </p>

                <p>
                    <label>Bangsa :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.kodBangsa.nama}&nbsp;
                </p>


                <p>
                    <label>Jantina :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.kodJantina.nama}&nbsp;
                </p>

                <p>
                    <label>Jenis Warganegara :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.kodWarganegara.nama}&nbsp;
                </p>

                <p>
                    <label>No Tel Rumah :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.noTelefon}&nbsp;
                </p>

                <p>
                    <label>No Tel Bimbit :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.noTelefonBimbit}&nbsp;
                </p>


                <p>
                    <label>Hubungan :</label>&nbsp;
                    ${actionBean.warisOrangKenaSyak.hubungan}&nbsp;
                </p>



                <p><label>&nbsp;</label>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
