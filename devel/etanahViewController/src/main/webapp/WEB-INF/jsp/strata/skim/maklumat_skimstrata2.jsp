<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
KEMASUKAN MAKLUMAT PENAMATAN SKIM STRATA
<s:form beanclass="etanah.view.strata.skim_strata">
    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Skim Strata</legend><br>
                <p>

                <label>Tarikh Akhir Mohon :</label>

            </p><br>
           
        </fieldset>
    </div>
<br>

 <div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Bangunan</legend>
            <p>

                <label>Nama Pemaju :</label>

            </p><br>
            <p>
                <label>Kategori Bangunan :</label>

            </p><br>
          
             <p>
                <label>Bilangan Bangunan :</label>

            </p><br>
             <p>
                <label>Bilangan Blok Sementara :</label>

            </p>
            <br>
        </fieldset>
    </div>

<br>

<div class="subtitle">
        <fieldset class="aras1">

            <legend>Pelan Bangunan</legend>
            <p>

                <label>Nombor Pelan Bangunan :</label>

            </p><br>
            <p>
                <label>Tarikh Bangunan Diluluskan :</label>

            </p><br><br>

        </fieldset>
    </div>
<br>
<div class="subtitle">
        <fieldset class="aras1">

            <legend>Maklumat Tarikh Jualan Petak</legend>
            <p>

                <label>Nombor Petak :</label><s:select name="no_petak" ></s:select>

            </p>
            <p>
                <label>Tarikh Jualan Petak :</label>
 <s:text name="tarikhjual" id="datepicker" class="datepicker"/> cth : 31/01/2009
            </p><br>
<p>

                <label>Rujukan Perjanjian Jual Beli :</label><s:text name="rujukan" ></s:text>

            </p><p>
                <label>&nbsp;</label>
               <a href="" <s:submit name="Kemaskini" value="Kemaskini" class="btn"/></a>
            </p><br>

            <div class="content" align="center">
            <display:table class="tablecloth" name=""  id="line">
                <display:column title="Nama Bangunan" />
                <display:column title="Senarai Tingkat-tingkat Dalam Bangunan" />
                 <display:column title="Senarai Petak-petak Dalam Tingkat"/>
                <display:column title="Tarikh Jualan Petak" />
                 <display:column title="Rujukan Perjanjian Jual Beli"/>

            </display:table>
        </div>
                <p>
                <label>&nbsp;</label>
               <a href="" <s:submit name="Kemaskini" value="Imbas" class="btn"/></a>
            </p>
        </fieldset>
    </div>

<br>

<div class="subtitle">
        <fieldset class="aras1">

            <legend>Sijil Layak Menduduki Bangunan</legend>
            <p>

                <label>Jenis Sijil :</label>

            </p><br><p>

                <label>Nombor Sijil :</label>

            </p><br>
            <p>
                <label>Tarikh Sijil Dikeluarkan :</label>
 
            </p><br>
<p>
                <label>&nbsp;</label>
                <a href="skim_strata?showForm4" <s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href="" <s:submit name="isisemula" value="Isi Semula" class="btn" /></a>
                <a href="" <s:submit name="Keluar" value="Keluar" class="btn"/></a>
            </p>
        </fieldset>
    </div>
 

    </s:form>