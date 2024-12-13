<%-- 
    Document   : maklumat_agensi
    Created on : Feb 22, 2010, 10:57:14 AM
    Author     : farah.shafilla
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
        <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Agensi Yang Terlibat
                        </legend>
                            <div class="content"  align="center">                       
                                     <display:table class="tablecloth" name="" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                                        <display:column title="Bil"></display:column>
                                        <display:column title="Agensi Yang Terlibat"></display:column>
                                        <display:column title="Alamat"></display:column>
                                        <display:column title="Catatan"></display:column>
                                    </display:table>
                                </div>
                            </fieldset>
                            </div>                    
    </s:form>