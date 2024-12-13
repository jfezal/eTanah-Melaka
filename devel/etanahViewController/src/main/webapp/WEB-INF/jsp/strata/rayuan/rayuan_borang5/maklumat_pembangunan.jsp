<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


PENYEDIAAN KERTAS PERAKUAN
<s:form beanclass="etanah.view.strata.rayuan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembangunan</legend>
            <p>
                <label>Nama Tuan Tanah :  </label>

            </p><br>
             <h5>Maklumat Bangunan</h5>
             <br>
                   <display:table  class="tablecloth" name=""  id="line">

                <display:column property="nama" title="Nama Bangunan" class=""/>
                <display:column property="" title="Tarikh Asal Bangunan Dijangka Siap"/>
                <display:column property="" title="Nombor Sijil Layak Menduduki" class=""/>
                <display:column property="" title="Tarikh Sijil Layak Menduduki"/>
                </display:table>
        </fieldset>
    </div>
    <br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik Strata Sementara</legend>
          <br>
                <display:table  class="tablecloth" name=""  id="line">
                <display:column property="" title="ID Hakmilik Yang Terlibat" class=""/>
                </display:table>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Ukur</legend>
            <p>
                <label>Tarikh Pelan Strata Diluluskan Oleh Pengarah Ukur :</label>&nbsp;

            </p><br>
            <p>
                <label>Bayaran Ukur (RM) :</label>

            </p><br>
            <p>
                <label>Bayaran Pelan (RM) :</label>

            </p><br>
            

        </fieldset>
    </div>
    <br>
         <label>&nbsp;</label>
              <a href="rayuan?showForm35"<s:submit name="Terus" value="Terus" class="btn"/></a>
               <a href=""<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                


        </s:form>