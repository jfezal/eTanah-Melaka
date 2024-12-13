<%-- 
    Document   : maklumat_tanah_pajakkecil
    Created on : Mar 11, 2010, 4:05:10 PM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form action="">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah/ Pajak/ Pajakan Kecil
            </legend>
             <div class="content"  align="center">
                        <p>
                            <label> Daerah :</label>&nbsp;
                        </p>

                    <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Bil" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Bandar/Pekan/Mukim" />
                    <display:column title="Nombor Lot" />
                    <display:column title="Hakmilik" />
                    <display:column title="Keluasan"/>
                    <display:column title="Jenis Pengggunaan Tanah" />
                    <display:column title="Kod Syarat Nyata" />
                    <display:column title="Kod Sekatan Kepentingan" />
                 </display:table>
            </div>
       </fieldset>
    </div><br>
 </s:form>
