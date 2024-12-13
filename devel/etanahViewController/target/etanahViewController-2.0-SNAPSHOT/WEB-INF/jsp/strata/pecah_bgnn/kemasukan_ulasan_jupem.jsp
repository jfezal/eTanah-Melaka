<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
KEMASUKAN ULASAN DARI JUPEM BAGI PERMOHONAN PECAH BAHAGI BANGUNAN
<s:form beanclass="etanah.view.strata.urusan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan Dari JUPEM</legend>
            <p>
                <label>Tarikh :</label>

            </p><br>
            <p>
                <label>Nombor Rujukan JUPEM :</label><s:text name="no_rujukan" ></s:text>

            </p>
            <p>
                <label>Nama Penyelia :</label><s:text name="penyelia" ></s:text>

            </p>
             <p>
                <label>Ulasan/Perakuan :</label> <s:textarea name="ulasan" cols="70" rows="5"/>

            </p>
             <p>
                <label>Bayaran Ukur(RM) :</label><s:text name="bayar_ukur" ></s:text>

            </p>
             <p>
                <label>Bayaran Pelan(RM) :</label><s:text name="bayar_pelan" ></s:text>
              </p>
             <p>


        </fieldset>
    </div>

<br>
               <p> <label>&nbsp;</label>
              <a href="urusan?showForm4"<s:submit name="Terus" value="Terus" class="btn"/></a>
                <a href="urusan?showForm3"<s:submit name="isisemula" value="Isi Semula" class="btn"/></a>

            </p>


    </s:form>