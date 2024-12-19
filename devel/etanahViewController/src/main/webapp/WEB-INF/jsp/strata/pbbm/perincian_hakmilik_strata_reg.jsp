<%-- 
    Document   : perincian_hakmilik_strata_reg
    Created on : Aug 27, 2012, 12:33:24 PM
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<!DOCTYPE html>
<script type="text/javascript">  
    function validateNumber2(elmnt,content) {
        //if it is character, then remove it..

        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function removeNonNumeric( strString )
            {
                var strValidCharacters = "1234567890";
                var strReturn = "";
                var strBuffer = "";
                var intIndex = 0;
                // Loop through the string
                for( intIndex = 0; intIndex < strString.length; intIndex++ )
                {
                    strBuffer = strString.substr( intIndex, 1 );
                    // Is this a number
                    if( strValidCharacters.indexOf( strBuffer ) > -1 )
                    {
                        strReturn += strBuffer;
                    }
                }
                return strReturn;
            }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<%--<s:form beanclass="etanah.view.strata.StrataMaklumatHakmilikPermohonanActionBean" id="kemasukanPerincianHakmilik">--%>
<s:form beanclass="etanah.view.strata.StrataMaklumatHakmilikPermohonanActionBean">    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perincian Hakmilik</legend>            
            <p><label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}
                &nbsp;
            </p> 
            <legend>Maklumat Strata</legend>
            <c:if test="${actionBean.kodNegeri ne '05'}">
            <p><label>No Bangunan :</label>
                
                <s:text name="hakmilik.noBangunan" readonly="true"/>
            </p>
            <p><label>No Tingkat :</label>
                <s:text name="hakmilik.noTingkat" readonly="true"/>
            </p>
            <p><label>No Petak :</label>
                <s:text name="hakmilik.noPetak" readonly="true"/>
            </p>          

            <p><label>Petak Aksesori :</label>
                <s:text name="petakAksesori" readonly="true"/>
            </p>
            <p><label>Unit Syer bagi Petak :</label>
                <s:text name="hakmilik.unitSyer" readonly="true"/>
            </p>
            </c:if>
             <c:if test="${actionBean.kodNegeri eq '05'}">
            
            <p><label>No Bangunan :</label>
                  <s:text name="noBangunan" id="noBangunan" readonly="true"/>                         
            </p>
            <p><label>No Tingkat :</label>                
                  <s:text name="noTingkat" id="noTingkat" readonly="true"/>                                
            </p>
            <p><label>No Petak :</label>              
                  <s:text name="noPetak" id="noPetak" readonly="true"/>                                
            </p>          
            <p><label>Petak Aksesori :</label>
                <s:text name="petakAksesori2" readonly="true"/>
            </p>
            <p><label>Unit Syer bagi Petak :</label>
                <s:text name="hakmilik.unitSyer" readonly="true"/>
            </p>
            </c:if>
            
            <%--<p>
                <label>Syarat Nyata :</label>
                <s:textarea name="syaratnyata" rows="5" style="width:40%;" readonly="true" id="syaratnyatadetails">Tertakluk Kepada Syarat Dalam Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.idHakmilikInduk}</s:textarea>
            </p>
            <p>
                <label>Sekatan Kepentingan :</label>
                <s:textarea name="sekatan" rows="5" style="width:40%;" readonly="true" id="sekatandetails" >Tertakluk Kepada Sekatan Dalam Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.idHakmilikInduk}</s:textarea>
            </p>--%>
            <c:if test="${actionBean.stageId eq 'kemasukan'}">
                <p>
                    <label>No PA(B) :</label>
                   <%-- <s:text name="noPelan1" id="noPelan1" onkeyup="validateNumber2(this,this.value);"/>--%>
                    <s:text name="noPelan1" id="noPelan1"/>
                </p>
            </c:if>
            <c:if test="${actionBean.stageId ne 'kemasukan'}">
                <p>
                    <label>No PA(B) :</label>
                    <s:text name="noPelan1" id="noPelan1" readonly= "true"/>
                </p>
            </c:if>
            <%--  <p><label>Jumlah Unit Syer bagi semua bangunan yang dipecah bahagikan atas tanah :</label>
                  <s:text name="hakmilik.jumlahUnitSyer" readonly="true"/>
              </p>  --%>
        </fieldset>
    </div>
    <s:hidden name="idHakmilik1" value="${actionBean.hakmilik.idHakmilik}"/>

    <c:if test="${actionBean.stageId eq 'kemasukan'}">
        <p><label>&nbsp;</label><s:button name="simpanHakmilikStrataReg" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></p>
    </c:if>
    <%--<br />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Pemilik</legend> <br />
            <display:table class="tablecloth" name="${actionBean.list}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">

                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column  title="Nama" class="nama">
                    ${line.pihak.nama}
                </display:column>
                <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                <display:column title="Alamat" >${line.pihak.alamat1}
                    <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                    ${line.pihak.alamat2}
                    <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                    ${line.pihak.alamat3}
                    <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                    ${line.pihak.alamat4}</display:column>
                <display:column property="pihak.poskod" title="Poskod" />
                <display:column property="pihak.negeri.nama" title="Negeri" />
            </display:table>
        </fieldset>
    </div>--%>
</s:form>
