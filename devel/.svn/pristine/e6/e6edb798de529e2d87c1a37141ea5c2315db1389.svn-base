<%-- 
    Document   : dev_kertasMMK_keadaanTanah
    Created on : May 26, 2014, 9:38:56 PM
    Author     : khairul.hazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:2px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript"> 
    function validateNumber(elmnt, content) {
        alert("a");
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.stripes.pembangunan.KertasMMKActionBean">
    <s:messages/>
    <s:errors/>

    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
    <s:hidden name="idLapor" value="${actionBean.idLapor}"/>

    <div class ="subtitle">
        <fieldset class="aras1">
            <tr></tr><tr></tr><tr></tr>
            <td>
                <table border="0"> 
                    <br></br>
                    <tr>
                        <td id="tdLabel"><font color="black">5.1)  Kedudukan Tanah :</font></td>
                        <td id="tdDisplay">
                            <s:textarea name="kedudukanTanah" rows="5" cols="100" class="normal_text"/>
                            <%--<s:text name="kedudukanTanah" id="kedudukanTanah" size="35" class="normal_text"/>--%>

                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><font color="black">Keadaan Tanah :</font></td>
                        <td id="tdDisplay">                                                                                            
                            <s:select name="keadaanTanah" id="keadaanTanah">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKeadaanTanah}" label="nama" value="kod" />
                            </s:select>

                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><font color="black">Tanaman :</font></td>
                        <td id="tdDisplay">
                            <s:select name="tanaman" id="tanaman" value = "${line.usaha}" style="">                                                               
                                <s:option label="Ya"  value="Y" />  
                                <s:option label="Tiada"  value="T" />                                   
                            </s:select>

                        </td>
                    </tr>
                    <tr>
                        <td id="tdLabel"><font color="black">Bangunan :</font></td>
                        <td id="tdDisplay">
                            <s:select name="bangunan" id="bangunan" value = "${line.adaBangunan}" style="">                                                               
                                <s:option label="Ya"  value="Y" />  
                                <s:option label="Tiada"  value="T" />                                   
                            </s:select>
                        </td>
                    </tr>                                    
                </table>
            </td>
            </tr>

            <br></br>
            <tr><td width="80%" align="top"><font color="black"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.2) Keadaan sekeliling tanah adalah seperti berikut : </b></td></tr>
            <br></br>
            <tr>
                <td>
                    <table border="0" width="96%" cellspacing="5">
                        <tr>
                            <!--                            <td width="50%" align="top"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.2) </b></td>-->
                            <!--Keadaan sekeliling tanah adalah seperti berikut :-->
                            <td width="50%">
                                <table class="tablecloth">


                                    <tr>
                                        <th>&nbsp;</th><th>Jenis Tanah</th><th>Kod Lot</th><th>Nombor Lot</th><th>Kegunaan</th><th>Catatan</th>
                                    </tr>

                                    <div align ="denter">
                                        <!--utara-->
                                        <tr>
                                            <th>Utara</th>                                      
                                            <td>
                                                <s:select name="milikKjaanUtara" style="">                                                               
                                                    <s:option label="Kerajaan"  value="Y" />  
                                                    <s:option label="Milik"  value="T" />  
                                                    <s:option label="Rizab"  value="R" /> 
                                                </s:select>
                                            </td>
                                            <td>
                                                <s:select name="kodLotUtara" style="">                                                               
                                                    <s:option label="Lot"  value="1" />  
                                                    <s:option label="PT"  value="3" />                                                     
                                                </s:select>
                                            </td>
                                            <td><s:text name="noLotUtara" size="35" class="normal_text"/></td>
                                            <td><s:text name="gunaUtara" size="35" class="normal_text"/></td>
                                            <td><s:text name="catatanUtara" size="35" class="normal_text"/></td>
                                        </tr>

                                        <!--timur-->
                                        <tr>
                                            <th>Timur</th>                                      
                                            <td>
                                                <s:select name="milikKjaanTimur" style="">                                                               
                                                    <s:option label="Kerajaan"  value="Y" />  
                                                    <s:option label="Milik"  value="T" />  
                                                    <s:option label="Rizab"  value="R" /> 
                                                </s:select>
                                            </td>
                                            <td>
                                                <s:select name="kodLotTimur" style="">                                                               
                                                    <s:option label="Lot"  value="1" />  
                                                    <s:option label="PT"  value="3" />                                                     
                                                </s:select>
                                            </td>
                                            <td><s:text name="noLotTimur" size="35" class="normal_text"/></td>
                                            <td><s:text name="gunaTimur" size="35" class="normal_text"/></td>
                                            <td><s:text name="catatanTimur" size="35" class="normal_text"/></td>
                                        </tr>

                                        <!--selatan-->
                                        <tr>
                                            <th>Selatan</th>                                      
                                            <td>
                                                <s:select name="milikKjaanSelatan" style="">                                                               
                                                    <s:option label="Kerajaan"  value="Y" />  
                                                    <s:option label="Milik"  value="T" />  
                                                    <s:option label="Rizab"  value="R" /> 
                                                </s:select>
                                            </td>
                                            <td>
                                                <s:select name="kodLotSelatan" style="">                                                               
                                                    <s:option label="Lot"  value="1" />  
                                                    <s:option label="PT"  value="3" />                                                     
                                                </s:select>
                                            </td>
                                            <td><s:text name="noLotSelatan" size="35" class="normal_text"/></td>
                                            <td><s:text name="gunaSelatan" size="35" class="normal_text"/></td>
                                            <td><s:text name="catatanSelatan" size="35" class="normal_text"/></td>
                                        </tr>         

                                        <!--barat-->
                                        <tr>
                                            <th>Barat</th>                                      
                                            <td>
                                                <s:select name="milikKjaanBarat" style="">                                                               
                                                    <s:option label="Kerajaan"  value="Y" />  
                                                    <s:option label="Milik"  value="T" />  
                                                    <s:option label="Rizab"  value="R" /> 
                                                </s:select>
                                            </td>
                                            <td>
                                                <s:select name="kodLotBarat" style="">                                                               
                                                    <s:option label="Lot"  value="1" />  
                                                    <s:option label="PT"  value="3" />                                                     
                                                </s:select>
                                            </td>
                                            <td><s:text name="noLotBarat" size="35" class="normal_text"/></td>
                                            <td><s:text name="gunaBarat" size="35" class="normal_text"/></td>
                                            <td><s:text name="catatanBarat" size="35" class="normal_text"/></td>
                                        </tr>
                                    </div>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

            <br></br>
            <div align ="center">
                <tr>                                                
                    <td>
                        <s:submit name="simpanKeadaanTanah" id="simpan" value="Simpan" class="btn"/>                        
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </td> 
                </tr>
            </div>

        </fieldset>
    </div>
</s:form>