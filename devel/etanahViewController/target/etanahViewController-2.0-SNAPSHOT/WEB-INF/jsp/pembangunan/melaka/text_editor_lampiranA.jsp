<%-- 
    Document   : rencana_Ringkasan_LampiranA_TSPSS_SBMS_PopupForPenjenisan
    Created on : Feb 20, 2014, 10:17:50 PM
    Author     : khairul.hazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<!--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>-->
<!--<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>-->
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script src=”http://tinymce.cachefly.net/4.0/tinymce.min.js”></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/jQuery-TE_v.1.4.0/jquery-te-1.4.0.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/jQuery-TE_v.1.4.0/jquery-te-1.4.0.min.js" charset="utf-8"></script>


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
    
    function searchKodSyaratNyata(idPlot){
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?showFormCariKodSyaratNyata&idPlot='+idPlot;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }

    function searchKodSekatan(index){
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }
    
    function save1(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPopup();
            self.close();
        },'html');       
    }
    
    function save2(){
        self.opener.refreshPopup();
        self.close();    
    }
</script>
<style type="text/css">
.btn1 {
    background: transparent url(${pageContext.request.contextPath}/images/orangebutton2.gif) no-repeat left top;
    /*border: 1px solid #000;*/
    border:0px;
    font-weight: bold;
    height:28px;
    width:110px;
    font-size:11px;
}
.btn1:hover {
    /* background:#FFC url(../images/orangehover.gif) repeat-x;
     background-position: 0 -50px;
     height:25px;*/
    background: transparent url(${pageContext.request.contextPath}/images/orangebuttonhover.gif) no-repeat left top;
    border:0px;
    font-weight: bold;
    height:28px;
    width:110px;
    cursor:hand;
}
.bckrnd {
      float: right;
    background: #fff;
    background: url("../images/contentbg.gif");
    background-position:center;
    background-repeat:no-repeat;
    background-attachment:fixed;
    margin-right: -165px;  /*** Same length as .outer border-left but with negative value ***/
    width: 100%;

}
</style>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>         
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pembangunan.RencanaRingkasanLampiranAForTSPSSnSBMSActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">


            <table border="0" width="85%" align="center" cellspacing="5">
                <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
                <s:hidden name="idPlot" value="${actionBean.idPlot}"/>
                <s:hidden name="foredit" value="${actionBean.foredit}"/>
                <s:hidden name="kodPemilikan" value="${actionBean.kodPemilikan}"/>

                <c:if test = "${actionBean.foredit eq 'E'}">
                    <tr>
                        <td width="22%"> &nbsp; Penjenisan/Kategori
                        <td><b>: </b> </td>
                        <td>
                            <s:select name="kodKategoriTanah" id="kodKategoriTanah" value="${actionBean.mpp.kategoriTanah.kod}">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                            </s:select>
                        </td>
                    </tr>

                    <tr>
                        <td> &nbsp; Jenis Hakmilik   </td>
                        <td><b>: </b> </td>
                        <td>
                            <s:select name="jenisHakmilik" id="jenisHakmilik" value="${actionBean.mpp.kodHakmilikTetap.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapMelaka}" label="nama" value="kod"/>
                            </s:select>
                        </td>
                    </tr>

                    <tr>
                        <td> &nbsp; Tempoh (Tahun)  </td> 
                        <td><b>: </b> </td>
                        <td>
                            <s:textarea rows="3" cols="80" name="tempoh" class="normal_text" onkeyup="validateNumber(this,this.value);"/>  
                        </td>
                        <td><em size="0">* Sila Inputkan Data 0 Jika Tempoh Adalah 0 </em></td>
                    </tr>

                    <tr>
                        <td> &nbsp; Premium    </td>
                        <td><b>: </b> </td>
                        <td>
                            <s:textarea rows="3" cols="80" name="premiumDesc" class="jqte-test"/>
                            <s:text name="premium" class="normal_text" size="20" onkeyup="validateNumber(this,this.value);"/>                     
                        </td>
                        <td>
                            <em>* Sila Inputkan Data 0 Jika Jumlah Premium Adalah 0 </em>
                        </td>
                    </tr>

                    <tr>
                        <td> &nbsp; Hasil    </td>
                        <td><b>:  </b></td>
                        <td>
                            <s:textarea rows="3" cols="80" name="hasilDesc" class="jqte-test"/>
                            <s:text name="hasil" class="normal_text" size="20" onkeyup="validateNumber(this,this.value);"/> 
                        </td>
                        <td>
                            <em>* Sila Inputkan Data 0 Jika Jumlah Hasil Adalah 0 </em>                 
                        </td>
                    </tr>

                    <tr>
                        <td> &nbsp; Jenis Penggunaan Tanah    </td>
                        <td><b>:  </b></td>
                        <td>
                            <s:select name="kodKegunaanTanah" id="kodKegunaanTanah" value="${actionBean.mpp.kegunaanTanah.kod}" style="width:600px;">                     
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKegunaanTanah}" label="nama" value="kod"/>
                            </s:select>
                        </td>
                    </tr>
                </table>
            </c:if>


            <div class="bckrnd" align ="center">
                <c:if test = "${actionBean.foredit eq 'NE'}">
                    <tr>
                        <td><b> &nbsp; Penjenisan/Kategori</b></td>
                        <td><b>: </b> </td>                       
                        <td><s:textarea rows="3" readonly="readOnly" cols="80" name="penjenisan" class="normal_text"/></td>                                                                    
                    </tr>

                    <tr>
                        <td> &nbsp; Jenis Hakmilik   </td>
                        <td><b>: </b> </td>                       
                        <td><s:textarea rows="3" readonly="readOnly" cols="80" name="kodHakmilik" class="normal_text"/></td>                                                                          
                    </tr>

                    <tr>
                        <td> &nbsp; Tempoh (Tahun)  </td>
                        <td><b>: </b> </td>                       
                        <td><s:textarea rows="3" readonly="readOnly" cols="80" name="tempoh" class="normal_text"/></td>                                            
                    </tr>

                    <tr>
                        <td> &nbsp; Premium    </td>
                        <td><b>: </b> </td>
                        <td><s:textarea rows="3" readonly="readOnly" cols="80" name="premiumDesc" class="normal_text"/></td>
                        <c:if test ="${actionBean.premium ne null}">
                            <td> = RM <s:textarea rows="1" readonly="readOnly" cols="15" name="premium" class="normal_text"/></td>                           
                        </c:if>
                        <c:if test ="${actionBean.premium eq null}">
                            <td></td>
                        </c:if>
                    </tr>

                    <tr>
                        <td> &nbsp; Hasil    </td>
                        <td><b>:  </b></td>
                        <td><s:textarea rows="3" readonly="readOnly" cols="80" name="hasilDesc" class="normal_text"/></td>
                        <c:if test ="${actionBean.hasil ne null}">
                            <td> = RM <s:textarea rows="1" readonly="readOnly" cols="15" name="hasil" class="normal_text"/></td> 
                        </c:if>
                        <c:if test ="${actionBean.hasil eq null}">
                            <td></td>
                        </c:if>                        
                    </tr>

                    <tr>
                        <td> &nbsp; Jenis Penggunaan Tanah    </td>
                        <td><b>:  </b></td>
                        <td><s:textarea rows="3" readonly="readOnly" cols="80" name="jenisGuna" class="normal_text"/></td>
                    </tr>

                    <tr>
                        <td> &nbsp; Syarat Nyata   </td>
                        <td><b>:  </b></td>
                        <td><s:textarea rows="3" readonly="readOnly" cols="80" name="kodSyaratNyataBaru" class="normal_text"/></td>
                    </tr>

                    <tr>
                        <td> &nbsp; Sekatan Kepentingan  </td>
                        <td><b>:  </b></td>
                        <td><s:textarea rows="8" readonly="readOnly" cols="80" name="kodSekatanKepentinganBaru" class="normal_text"/></td>
                    </tr>             
                </c:if>
            </div>



            <br><br>
            <c:if test ="${actionBean.foredit eq 'E'}">
                <tr >
                <div align="center">
                    <td><s:submit name="simpanPopup" id="save" value="Simpan" class="btn1"/> </td>
                    <td><s:button name="" id="tutup" value="Tutup" class="btn1" onclick="save2()"/></td>                         
                </div>
                </tr>
            </c:if>

            <c:if test ="${actionBean.foredit eq 'NE'}">
                <tr>
                <div align="center">                
                    <td><s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></td>                   
                </div>
                </tr>
            </c:if>
                <!------------------------------------------------------------ Toggle jQTE Button ------------------------------------------------------------>


<script>
	$('.jqte-test').jqte();
	
	// settings of status
//	var jqteStatus = true;
//	$(".status").click(function()
//	{
//		jqteStatus = jqteStatus ? false : true;
//		$('.jqte-test').jqte({"status" : jqteStatus})
//	});
</script>        </s:form>

