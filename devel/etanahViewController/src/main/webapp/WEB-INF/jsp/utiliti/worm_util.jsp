<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<s:form beanclass="etanah.view.utility.WORMActionUtil">    
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>

    <div class="subtitle" id="main">
        <fieldset class="aras1">
            <legend>Test WORM</legend>             
            <div id="test2">
                <p>
                    <label>ID Hakmilik :</label>
                    <s:text name="idHakmilik" size="30"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="searchHakmilik" id="search" value="Cari" class="btn"/>
                </p>
            </div>            
        </fieldset>


        <br/>
        <br/>
        <c:if test="${!empty actionBean.hakmilik}">
            <s:hidden name="hakmilik.idHakmilik"/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Keputusan
                    </legend>
                    <div>                        
                        <p>
                            <label>ID Dokumen :</label>
                            ${actionBean.hakmilik.dhke.idDokumen}
                        </p>
                        <p>
                            <label>Tajuk :</label>
                            ${actionBean.hakmilik.dhke.tajuk}
                        </p>
                        <p>
                            <label>Dokumen :</label>
                            ${actionBean.hakmilik.dhke.kodDokumen.nama}
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            <s:submit name="testWorm" value="TEST" class="btn"/>
                        </p>

                    </div>
                </fieldset>
            </div>
        </c:if>

    </div>

</s:form>

