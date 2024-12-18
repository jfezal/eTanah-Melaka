<%-- 
    Document   : diari_penyiasatan
    Created on : Jan 6, 2011, 10:44:13 AM
    Author     : zadhirul.farihim
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>.:ETanah:.</title>
        <script type="text/javascript">

            function tambahSiasatan() {
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);
                var url = '${pageContext.request.contextPath}/strata/kuatkuasa?tambahSiasatan';
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=800,height=250, left=" + left + ",top=" + top);
            }


        </script>


    </head>
    <body>
        <s:form beanclass="etanah.view.strata.PenguatkuasaanStrataActionBean">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Aduan</legend>
                    <p>
                        Yang bertanda(<em>*</em>) adalah wajib diisi.
                    </p>
                    <p>
                        <label>No Aduan : </label><s:text readonly="true" name="idPermohonan" size="25"class="normal_text"/>

                    </p>
                    <p>
                        <label>Penguatkuasaan : </label><s:text readonly="true" name="nama" size="50" class="normal_text"/>

                    </p>
                    <p>
                        <label>Kesalahan : </label><s:textarea name="mhnStrataKemudahan" class="normal_text" rows="5" cols="50"/>

                    </p>
                    <p>
                        <label><em>*</em>Pegawai Penyiasatan : </label><s:text name="mhnStrataKemudahan" size="50"class="normal_text"/>

                    </p>
                    <p>
                        <label><em>*</em>Pangkat : </label><s:text name="mhnStrataKemudahan" size="50"class="normal_text"/>

                    </p>
                </fieldset>
            </div>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Penyiasatan</legend>
                    <p>&nbsp; </p>
                    <p>
                        <a href="#bottom">
                            <s:button name="Tambah" value="Tambah Siasatan" class="longbtn" onclick="tambahSiasatan();" /></a>
                    </p>
                    <p>&nbsp; </p>
                    <center>
                        <table class="tablecloth" align="center">
                            <tr style="width: 100%">
                                <th>Bil.</th>
                                <th>Tarikh dan Masa</th>
                                <th style="width:300px;" >Perihal Siasatan</th>
                                <th>Hapus Siasatan</th>
                            </tr>
                            <c:set var="items" value="0"/>

                            <%--<c:forEach items="${actionBean.mohonBangunanList}" var="bgn" varStatus="status">
                                <tr>
                                    <td><s:text name="mohonBangunanList[${items}].nama" size="10"/></td>
                                    ${bgn.kategoriBangunan}${bgn.nama}</td>
                                    <td><s:text name="mohonBangunanList[${items}].bilanganPetak" size="10"/></td>
                                    ${bgn.bilanganPetak}</td>
                                    <td>
                                        <s:text name="mohonBangunanList[${items}].syerBlok" size="10"/></td>
                                        ${bgn.syerBlok}</td>
                                    <td>
                                        <s:text name="bngn" size="10" class="datepicker"/></td>
                                        <s:text name="bgn" size="10" class="datepicker" onchange="validateDate(this,this.value);"/></td>
                                </tr
                                <c:set var="items" value="${items+1}"/>

                        </c:forEach>--%>
                        </table>
                    </center>
                    <p>&nbsp; </p>

                    <p>
                        <label>&nbsp;</label>&nbsp;
                        <s:submit class="btn" value="Simpan" name="simpanSiasatan"/>

                    </p>
                </fieldset>
            </div>

        </s:form>
    </body>
</html>
