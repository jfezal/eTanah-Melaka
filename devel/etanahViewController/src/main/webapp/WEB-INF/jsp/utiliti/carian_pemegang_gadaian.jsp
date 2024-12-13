
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<s:form beanclass="etanah.view.utility.CarianPemegangGadaianActionBean" name="carianPemegangGadaian" id="carianPemegangGadaian">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <div class="content">
            <fieldset class ="aras1">
                <legend>Carian Permohonan</legend>
                <p><label><font color="red">*</font>ID Permohonan :</label>
                        <s:text id="idPermohonan" name="idPermohonan"></s:text>
                    </p>
                    <p align="center"><s:submit name="carianPemegangGadaian" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('carianPemegangGadaian')" /></p>

            </fieldset>
        </div>

        <div class="content">
            <fieldset class="aras1">
                <legend>
                    Senarai Permohonan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listMP}"
                                   id="line" style="width:95%"
                                   requestURI="${pageContext.request.contextPath}/utiliti/carian_pemegang_gadaian">                    
                        <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                            ${line_rowNum}
                        </display:column>
                        <display:column title="ID Permohonan">
                            <c:if test="${line.perserahan.idPerserahan ne null}">
                                ${line.perserahan.idPerserahan}
                            </c:if>
                        </display:column>
                        <display:column title="Urusan">                        
                            ${line.perserahan.kodUrusan.nama}
                        </display:column>
                        <display:column title="Pemegang Gadaian Semasa">                        
                            ${line.nama}                    
                        </display:column>
                        <display:column title="No Pengenalan">                        
                            ${line.noPengenalan}              
                        </display:column>   
                        <display:column title="Kod Pengenalan">                        
                            ${line.jenisPengenalan.nama}              
                        </display:column>  
                        <display:column> <s:radio name="radio" id="radio" value="${line.idHakmilikPihakBerkepentingan}"/>
                            <s:hidden value = "${actionBean.idPermohonan}" name = "idMohon"/>
                        </display:column>                       
                    </display:table>
                </div>

            </fieldset>
        </div>
        <div class="content">
           <%-- <fieldset class="aras1">
                <legend>
                    Senarai Pemegang Gadaian
                </legend>--%>
           <div class="content" align="center">
                   <%-- <p>
                        <label>Pemegang Gadaian :</label>
                        <s:select name="namaPG" id="namaPG" >
                            <s:option value="0">Pilih</s:option>
                            <s:options-collection collection="${actionBean.listPG}" label="nama" value="nama"/>                          
                        </s:select>                            
                        <s:hidden value = "${actionBean.idPermohonan}" name = "idPermohonan"/>
                        <s:hidden value = "${actionBean.idHM}" name = "idHM"/>
</p>    --%>
                    <p align="left"><s:submit name="simpan" value="Simpan" class="btn"/></p> 
                </div>
</div>
    </div> 
</s:form>