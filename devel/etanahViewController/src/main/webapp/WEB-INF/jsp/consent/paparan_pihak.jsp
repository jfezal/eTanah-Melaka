<%-- 
    Document   : paparan_pihak
    Created on : Jan 19, 2011, 10:18:29 AM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>

<s:form beanclass="etanah.view.stripes.consent.KemasukanWarisActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Waris
            </legend>
            <p>
                <label>Nama :</label>
                <c:if test="${actionBean.pihak.nama ne null}">  <font style="text-transform:uppercase;">${actionBean.pihak.nama}&nbsp;</font> </c:if>
                <c:if test="${actionBean.pihak.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <c:if test="${actionBean.pihak.jenisPengenalan.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.jenisPengenalan.nama}&nbsp;</font> </c:if>
                <c:if test="${actionBean.pihak.jenisPengenalan.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                <c:if test="${actionBean.pihak.noPengenalan ne null}"> ${actionBean.pihak.noPengenalan}&nbsp; </c:if>
                <c:if test="${actionBean.pihak.noPengenalan eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Bangsa :</label>
                <c:if test="${actionBean.pihak.bangsa.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.bangsa.nama}&nbsp;</font> </c:if>
                <c:if test="${actionBean.pihak.bangsa.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label for="Suku">Jenis Suku :</label>
                <c:if test="${actionBean.pihak.suku.nama ne null}"> <font style="text-transform:uppercase;"> ${actionBean.pihak.suku.nama}&nbsp;</font> </c:if>
                <c:if test="${actionBean.pihak.suku.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label for="luak">Luak :</label>
                <c:if test="${actionBean.pihak.tempatSuku ne null}">  <font style="text-transform:uppercase;">${actionBean.pihak.tempatSuku}&nbsp;</font> </c:if>
                <c:if test="${actionBean.pihak.tempatSuku eq null}"> TIADA DATA </c:if>
            </p>
            <p>
                <label>Hubungan :</label>
                <c:if test="${actionBean.permohonanPihak.kaitan ne null}"> <font style="text-transform:uppercase;"> ${actionBean.permohonanPihak.kaitan}&nbsp;</font> </c:if>
                <c:if test="${actionBean.permohonanPihak.kaitan eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Umur :</label>
                <c:if test="${actionBean.permohonanPihak.umur ne null}"> <font style="text-transform:uppercase;"> ${actionBean.permohonanPihak.umur}&nbsp;</font> </c:if>
                <c:if test="${actionBean.permohonanPihak.umur eq null}"> Tiada Data </c:if>
            </p>


            <c:if test="${actionBean.pihak.alamat1 eq null}">
                <p>
                    <label>Alamat Berdaftar :</label>
                    Tiada Data&nbsp;
                </p>
            </c:if>

            <c:if test="${actionBean.pihak.alamat1 ne null}">
                <p>
                    <label>Alamat Berdaftar :</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.alamat1}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.alamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;"> ${actionBean.pihak.alamat2}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.alamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.alamat3}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.alamat4 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;"> ${actionBean.pihak.alamat4}&nbsp;</font>
                </p>
            </c:if>
            <p>
                <label>Poskod :</label>
                <c:if test="${actionBean.pihak.poskod ne null}"> ${actionBean.pihak.poskod}&nbsp; </c:if>
                <c:if test="${actionBean.pihak.poskod eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Negeri :</label>
                <c:if test="${actionBean.pihak.negeri.nama ne null}"> <font style="text-transform:uppercase;">${actionBean.pihak.negeri.nama}</font>&nbsp; </c:if>
                <c:if test="${actionBean.pihak.negeri.nama eq null}"> Tiada Data </c:if>
            </p>
            <c:if test="${actionBean.pihak.suratAlamat1 ne null}">
                <p>
                    <label>Alamat Surat-Menyurat :</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.suratAlamat1}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.suratAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;"> ${actionBean.pihak.suratAlamat2}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.suratAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">   ${actionBean.pihak.suratAlamat3}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.suratAlamat4 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.suratAlamat4}&nbsp;</font>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.suratPoskod ne null}">
                <p>
                    <label>Poskod :</label>
                    <c:if test="${actionBean.pihak.suratPoskod ne null}"> ${actionBean.pihak.suratPoskod}&nbsp; </c:if>
                    <c:if test="${actionBean.pihak.suratPoskod eq null}"> Tiada Data </c:if>
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.suratNegeri ne null}">
                <p>
                    <label>Negeri :</label>
                    <c:if test="${actionBean.pihak.suratNegeri.nama ne null}"><font style="text-transform:uppercase;"> ${actionBean.pihak.suratNegeri.nama}</font>&nbsp; </c:if>
                    <c:if test="${actionBean.pihak.suratNegeri.nama eq null}"> Tiada Data </c:if>
                </p>
            </c:if>
            <br/>
            <p align="center">
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br/>
        </fieldset>
    </div>
</s:form>
