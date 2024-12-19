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
            <legend>Jadual Petak-petak dan Blok Sementara</legend>
            <p>
                <label>Jumlah Bangunan :  2</label>

            </p><br>
            <display:table  class="tablecloth" name=""  id="line">

                <display:column property="nama" title="Nama Bangunan" class=""/>
                <display:column property="" title="Senarai Tingkat-tingkat Dalam Bangunan"/>
                <display:column property="" title="Senarai Petak-petak Dalam Tingkat" class=""/>
                <display:column property="" title="Unit-unit Syer bagi setiap petak/blok sementara"/>
                <display:column property="" title="Kegunaan Petak"/>

            </display:table>
                     
        </fieldset>
    </div>
    <br>
             <label>&nbsp;</label>
              <a href="rayuan?showForm32"<s:submit name="Terus" value="Terus" class="btn"/></a>
               <a href=""<s:submit name="Kembali" value="Kembali" class="btn"/></a>
                

        
        </s:form>