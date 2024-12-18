<%-- 
    Document   : semakanPermohonan
    Created on : May 13, 2011, 11:52:22 AM
    Author     : latifah.iskak
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />

    </head>
    <body>

        <stripes:messages />
        <stripes:errors />

        <p></p>
        <c:if test="${actionBean.fromPage eq 'statusPermohonan'}">
            <c:set var="callFromPage" value="/penguatkuasaan/status_permohonan"/>
        </c:if>
        <c:if test="${actionBean.fromPage eq 'senaraiDokumen'}">
            <c:set var="callFromPage" value="/penguatkuasaan/utiliti_senarai_dokumen"/>
        </c:if>
        <stripes:form action="${callFromPage}" id="main_kesinambungan">

            <fieldset class="aras1">

                <legend>Maklumat Permohonan Penguatkuasaan</legend>
                <br>
                <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                    <input type="text" name="permohonan.idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>
                <c:if test="${actionBean.fromPage eq 'statusPermohonan'}">
                    <label>&nbsp;&nbsp;</label>
                    <p>
                        <font color="red">ATAU</font>
                    </p>
                    <p><label for="bpm">Bandar/Pekan/Mukim :</label>
                        <stripes:select name="bpm" id="bpm">
                            <stripes:option value="">Pilih</stripes:option>
                            <stripes:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="nama" />
                        </stripes:select>
                        
                    </p>

                    <!--        
                    <input type="text" name="bpm" id="bpm"/><label>&nbsp;&nbsp;</label>
                                        <p>
                                            <font color="red">ATAU</font>
                                        </p>
                                        <p><label for="daerah">Daerah :</label>
                                            <input type="text" name="daerah" id="daerah"/>
                                        </p>
                    
                                        <label>&nbsp;&nbsp;</label>
                                        <p>
                                            <font color="red">ATAU</font>
                                        </p>
                                        <p><label for="jenisKesalahan">Jenis Kesalahan :</label>
                                            <input type="text" name="jenisKesalahan" id="jenisKesalahan"/>
                                        </p>
                    
                                        <label>&nbsp;&nbsp;</label>
                                        <p>
                                            <font color="red">ATAU</font>
                                        </p>
                                        <p><label for="lokasi">Lokasi :</label>
                                            <input type="text" name="lokasi" id="lokasi"/>
                                        </p>-->
                </c:if>


                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="checkPermohonan" value="Seterusnya" class="btn" />
                </p>

            </fieldset>
        </stripes:form>
    </body>
</html>
