<%-- 
    Document   : rencana_Ringkasan_LampiranA_TSPSS_SBMS
    Created on : Feb 20, 2014, 3:33:58 PM
    Author     : khairul.hazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    
    function dopopup(i,k){  
         var kodUrusan = '${actionBean.kodUrusan}';
        var d=i;
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?editPopup&idPlot="+d+"&foredit="+k+"&kodUrusan="+kodUrusan, "eTanah",
        "status=0,location=0");
    }
    
    function doNyata(i,k){  
        var d=i;
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?editNyata&idPlot="+d+"&foredit="+k, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=500");
    }
    
    function doSekatan(i,k){  
        var d=i;
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?editSekatan&idPlot="+d+"&foredit="+k, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=900");
    }
    
    function refreshPopup(){
        var id = '${actionBean.idPermohonan}';
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?rehydrate&idPermohonan='+id ;
        $.get(url,
        function(data){
            $("#popupDiv").replaceWith($('#popupDiv', $(data)));
        }
        ,'html');
    }
</script>


<s:form beanclass="etanah.view.stripes.pembangunan.RencanaRingkasanLampiranAForTSPSSnSBMSActionBean">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:messages/>
    <s:errors/>
    <%--<s:hidden name="kertasK.kertas.idKertas"/>--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="70%" align="center">
                    <c:if test = "${actionBean.fp eq null}">
                        <tr>
                             <td align="right"><b>LAMPIRAN 'A'</b></td>
                        </tr>
                    </c:if>
                </table>
                <br><br>
                <table border="0" width="85%" align="center" cellspacing="10">                  
                    <tr>
                        <td align="center"><b> SYARAT NYATA DIHAPUSKAN DAN DIGANTIKAN DENGAN SYARAT-SYARAT NYATA SEPERTI BERIKUT :- </b></td>
                    </tr>
                </table>

                <br/><br/>

                <div id ="popupDiv">
                    <table border="0" width="85%" align="center" cellspacing="5">
                        <tr>
                            <td align ="left"><b> I. &nbsp; Penjenisan/Kategori</b></td>
                        <tr>
                            <td align ="center">
                                <display:table class="tablecloth" name="${actionBean.listHakmilik}" cellpadding="0" requestURI="/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS"
                                               cellspacing="0" id="line">
                                    <display:column title="Bil" style="vertical-align:baseline">${line_rowNum}
                                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPlot}"/>
                                    </display:column>
                                    <display:column title="Penjenisan" style="vertical-align:baseline">
                                        <c:if test = "${line.kategoriTanah ne null}"> ${line.kategoriTanah.nama}&nbsp; </c:if>
                                        <c:if test = "${line.kategoriTanah eq null}"> - &nbsp; </c:if>
                                    </display:column> 
                                    <display:column title="Butir-Butir Pembangunan" style="vertical-align:baseline">
                                        <c:if test = "${line.catatan ne null}"> ${line.catatan}&nbsp; </c:if>
                                        <c:if test = "${line.catatan eq null}"> ${line.kegunaanTanahLain}&nbsp; </c:if>                                        
                                    </display:column> 
                                    <display:column title="Premium (RM)" style="vertical-align:baseline">
                                        <c:if test = "${line.plotPremium eq null}">0.00 &nbsp;</c:if>
                                        <c:if test = "${line.plotPremium ne null}"><fmt:formatNumber pattern="#,##0.00" value="${line.plotPremium}"/> &nbsp;</c:if>    
                                    </display:column> 
                                    <display:column title="Hasil (RM)" style="vertical-align:baseline">
                                        <c:if test = "${line.plotHasil eq null}">0.00 &nbsp;</c:if>
                                        <c:if test = "${line.plotHasil ne null}"><fmt:formatNumber pattern="#,##0.00" value="${line.plotHasil}"/> &nbsp;</c:if>  
                                    </display:column>    

                                    <c:if test ="${edit}">
                                        <display:column title="Kemaskini Nilai">
                                            <div align="center">                                      
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}','E');return false;"  onmouseover="this.style.cursor='pointer';">                                                                                    
                                            </div>
                                        </display:column>   
                                        <display:column title="Kemaskini Syarat Nyata">
                                            <div align="center">                                          
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doNyata('${line.idPlot}','NY');return false;"  onmouseover="this.style.cursor='pointer';">                                                                                 
                                            </div>
                                        </display:column>  
                                        <display:column title="Kemaskini Sekatan Kepentingan">
                                            <div align="center">                                           
                                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="doSekatan('${line.idPlot}','SK');return false;"  onmouseover="this.style.cursor='pointer';">                                                                              
                                            </div>
                                        </display:column>  
                                    </c:if>

                                    <c:if test = "${!edit}">
                                        <display:column title="Paparan Maklumat">
                                            <div align="center">                                                                                      
                                                <img alt='Paparan Maklumat' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                     id='rem_${line_rowNum}' onclick="dopopup('${line.idPlot}','NE');return false;"  onmouseover="this.style.cursor='pointer';">                                         
                                            </div>
                                        </display:column>  
                                    </c:if>
                                </display:table>
                            </td>
                        </tr>
                        </tr>
                    </table>


                    <table border="0" width="85%" align="center" cellspacing="5"> 
                        <c:if test="${edit}">
                            <tr>
                                <td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Jumlah Premium </b></td>
                                <td><b>:  </b></td>        
                                <c:if test ="${actionBean.jumlahPremium eq null}">
                                    <td>RM &nbsp; 0.00</td> 
                                </c:if>
                                <c:if test ="${actionBean.jumlahPremium ne null}">
                                    <td>RM &nbsp; <fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlahPremium}"/></td> 
                                </c:if>
                            </tr>
                            <tr>
                                <td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Jumlah Hasil </b></td>
                                <td><b>:  </b></td>  
                                <c:if test ="${actionBean.jumlahHasil eq null}">
                                    <td>RM &nbsp; 0.00</td> 
                                </c:if>
                                <c:if test ="${actionBean.jumlahHasil ne null}">
                                    <td>RM &nbsp; <fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlahHasil}"/></td> 
                                </c:if>
                            </tr>                   
                        </c:if>
                        <br>
                        <c:if test ="${edit}">
                            <tr>
                                <td><b>II. &nbsp; Sumbangan Saliran </b>   </td>
                                <td><b>:  </b></td>
                                <td>
                                    <s:textarea rows="3" cols="80" name="sumbanganSaliranDesc" class="normal_text" />
                                    <s:text name="sumbanganSaliran" class="normal_text" size="20" onkeyup="validateNumber(this,this.value);"/>
                                </td>
                            </tr>
                            <tr>
                                <td><b>III. &nbsp; Bayaran Ukur  </b>  </td>
                                <td><b>:  </b></td>
                                <td><s:textarea rows="3" cols="80" name="bayaranUkur" class="normal_text" /></td>
                            </tr>
                        </c:if>
                    </table>

                    <table border="0" width="35%" align="center" cellspacing="5">  
                        <c:if test ="${!edit}">
                            <tr>
                                <td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Jumlah Premium </b></td>
                                <td><b>:  </b></td>        
                                <c:if test ="${actionBean.jumlahPremium eq null}">
                                    <td>RM &nbsp; 0.00</td> 
                                </c:if>
                                <c:if test ="${actionBean.jumlahPremium ne null}">
                                    <td>RM &nbsp; <fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlahPremium}"/></td> 
                                </c:if>
                            </tr>
                            <tr>
                                <td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Jumlah Hasil </b></td>
                                <td><b>:  </b></td>  
                                <c:if test ="${actionBean.jumlahHasil eq null}">
                                    <td>RM &nbsp; 0.00</td> 
                                </c:if>
                                <c:if test ="${actionBean.jumlahHasil ne null}">
                                    <td>RM &nbsp; <fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlahHasil}"/></td> 
                                </c:if>
                            </tr> 
                            <tr>
                                <td><b>II. &nbsp; Sumbangan Saliran </b>   </td>
                                <td><b>:  </b></td>
                                <td>${actionBean.sumbanganSaliranDesc} = RM <fmt:formatNumber pattern="#,##0.00" value="${actionBean.sumbanganSaliran}"/></td>                               
                            </tr>
                            <tr>
                                <td><b>III. &nbsp; Bayaran Ukur  </b>  </td>
                                <td><b>:  </b></td>
                                <td>${actionBean.bayaranUkur}</td>
                            </tr>
                        </c:if>
                    </table>
                </div

                <c:if test = "${edit}">
                    <p align="center">
                        <s:button name="simpanSBMS" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>

            </div>
        </fieldset>
    </div>
</s:form>
