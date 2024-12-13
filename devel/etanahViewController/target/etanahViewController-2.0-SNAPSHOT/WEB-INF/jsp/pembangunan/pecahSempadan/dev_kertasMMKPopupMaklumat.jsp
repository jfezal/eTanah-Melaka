<%-- 
    Document   : dev_kertasMMKPopupMaklumat
    Created on : May 22, 2014, 4:01:26 PM
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
    $(document).ready(function() {

    });

    function searchKodSyaratNyata(index) {
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSyaratNyata&index=' + index;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function searchKodSekatan(index) {
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan&index=' + index;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function validateNumber(elmnt, content) {
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
    <s:hidden name="idPlot" value="${actionBean.idPlot}"/>

    <div class ="subtitle">
        <fieldset class="aras1">

            <c:if test = "${actionBean.forEdit eq 'Y' || actionBean.forEdit eq null}">
                <table border="0" width="70%" cellspacing="5" align="center">
                    <div align ="center">
                        <tr>
                            <td><b>Kategori</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.kategoriTanah.nama}</td>
                        </tr>

                        <%--
                        <tr>
                            <td><b>Lot</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.kegunaanTanah.nama}</td>
                        </tr>
                        --%>

                        <tr>
                            <td><b>Luas</b></td>
                            <td> : </td>
                            <td>Mengikut Pelan Tatatur ${actionBean.pelantatatur} ${actionBean.hakmilikPermohonan1.catatan}</font></td>
                        </tr>

                        <tr>
                            <td><b>Pegangan</b></td>
                            <td>:</td>
                            <td>
                                <s:select name="pegangan0" value="${actionBean.mpp.pegangan}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="S">Selama - lamanya</s:option>
                                    <s:option value="P">Pajakan</s:option>
                                </s:select>                               
                            </td>
                        </tr>

                        <tr>
                            <td><b>Tempoh Pegangan</b></td>
                            <td>:</td>
                            <td>
                                <s:text name="tempohPegangan" class="normal_text" onkeyup="validateNumber(this,this.value);"/> &nbsp; tahun
                            </td>
                        </tr>

                        <tr>                           
                            <td><b>Jenis Hakmilik Sementara</b></td>
                            <td>:</td>
                            <td>
                                <s:select name="janisHakmilikSementara" value="${actionBean.mpp.kodHakmilikSementara}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodHakmilikSementara}" label="nama" value="kod"/>
                                </s:select>
                            </td>
                        </tr>

                        <tr>

                            <td><b>Jenis Hakmilik Tetap</b></td>
                            <td>:</td>
                            <td>
                                <s:select name="jenisHakmilikTetap" value="${actionBean.mpp.kodHakmilikTetap}">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:options-collection collection="${listUtil.senaraiKodHakmilikTetapNS}" label="nama" value="kod"/>
                                </s:select></font>
                            </td>
                        </tr>

                        <tr>                            
                            <td><b>Kadar Cukai</b></td>
                            <td>:</td>
                            <td>
                                <s:textarea name="kadarCukai" rows="5" cols="100" class="normal_text"/>
                            </td>
                        </tr>

                        <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCBA' || actionBean.permohonan.kodUrusan.kod eq 'PYTN' || actionBean.permohonan.kodUrusan.kod eq 'PPCS')}">
                            <tr>                             
                                <td><b>Keterangan Kadar Premium</b></td>
                                <td>:</td>
                                <td>
                                    <s:textarea name="kadarPremium" rows="5" cols="100" class="normal_text"/>
                                </td>
                            </tr>

                            <tr>

                                <td><b>Kos Sumbangan Infrastuktur</b></td>
                                <td>:</td>
                                <td>
                                    <s:text name="kadarInfra" class="normal_text" onkeyup="validateNumber(this,this.value);"></s:text> Sehektar
                                </td>
                            </tr>
                        </c:if>

                        <tr>
                            <td><b>Kadar Bayaran Upah Ukur dan Batu Sempadan</b></td>
                            <td>:</td>
                            <td>
                                <s:textarea name="kadarUpah" rows="5" cols="100" class="normal_text" />
                            </td>
                        </tr>

                        <tr>
                            <td><b>Kadar Bayaran Pendaftaran dan Penyediaan Hakmilik Tetap/Sementara</b></td>
                            <td>:</td>
                            <td>
                                <s:textarea name="kadarDaftar" rows="5" cols="100" class="normal_text"/>
                            </td>
                        </tr>                       

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                            <tr>
                                <td><b>Ingatan</b></td>
                                <td>:</td>
                                <td>
                                    <s:textarea name="ingatan" rows="5" cols="100" class="normal_text"/>
                                </td>
                            </tr>
                        </c:if>

                        <tr></tr>
                        <tr></tr>
                        <tr>                                                
                            <td>
                                <s:submit name="simpanPopup" id="simpan" value="Simpan" class="btn"/>                        
                                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                            </td> 
                        </tr>
                    </div>
                </table>
            </c:if>


            <c:if test = "${actionBean.forEdit eq 'N'}">
                <table border="0" width="70%" cellspacing="5" align="center">
                    <div align ="center">
                        <tr>
                            <td><b>Kategori</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.kategoriTanah.nama}</td>
                        </tr>

                        <%--
                        <tr>
                            <td><b>Lot</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.kegunaanTanah.nama}</td>
                        </tr>
                        --%>

                        <tr>
                            <td><b>Luas</b></td>
                            <td> : </td>
                            <td>Mengikut Pelan Tatatur ${actionBean.pelantatatur} ${actionBean.hakmilikPermohonan1.catatan}</font></td>
                        </tr>

                        <tr>
                            <td><b>Pegangan</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.pegangan}</td>
                            </td>
                        </tr>

                        <tr>
                            <td><b>Tempoh Pegangan</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.tempohPegangan} &nbsp; Tahun</td>                               
                        </tr>

                        <tr>                           
                            <td><b>Jenis Hakmilik Sementara</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.kodHakmilikSementara.nama} </td>                             
                        </tr>

                        <tr>
                            <td><b>Jenis Hakmilik Tetap</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.kodHakmilikTetap.nama}</td>                                 
                        </tr>

                        <tr>                            
                            <td><b>Kadar Cukai</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.keteranganCukaiBaru}</td>
                        </tr>

                        <c:if test="${!(actionBean.permohonan.kodUrusan.kod eq 'PPCB' || actionBean.permohonan.kodUrusan.kod eq 'PPCBA' || actionBean.permohonan.kodUrusan.kod eq 'PYTN' || actionBean.permohonan.kodUrusan.kod eq 'PPCS')}">
                            <tr>                             
                                <td><b>Keterangan Kadar Premium</b></td>
                                <td>:</td>
                                <td>${actionBean.mpp.keteranganKadarPremium}</td>                                    
                            </tr>

                            <tr>
                                <td><b>Kos Sumbangan Infrastuktur</b></td>
                                <td>:</td>
                                <td>${actionBean.mpp.kosInfra}</td>
                            </tr>
                        </c:if>

                        <tr>
                            <td><b>Kadar Bayaran Upah Ukur dan Batu Sempadan</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.keteranganKadarUkur}</td>                               
                        </tr>

                        <tr>
                            <td><b>Kadar Bayaran Pendaftaran dan Penyediaan Hakmilik Tetap/Sementara</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.keteranganKadarDaftar}</td>                                
                        </tr>                       

                        <tr>
                            <td><b>Syarat Nyata</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.kodSyaratNyata.syarat}</td>
                        </tr>

                        <tr>
                            <td><b>Sekatan Kepentingan</b></td>
                            <td>:</td>
                            <td>${actionBean.mpp.kodSekatanKepentingan.sekatan}</td>
                        </tr>

                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                            <tr>
                                <td><b>Ingatan</b></td>
                                <td>:</td>
                                <td>${actionBean.mpp.peringatan}</td>                             
                            </tr>
                        </c:if>

                        <tr>
                            <td align ="center">                       
                                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>      
                            </td>
                        </tr>
                    </div>
                </table>
            </c:if>
        </fieldset>
    </div>
</s:form>
