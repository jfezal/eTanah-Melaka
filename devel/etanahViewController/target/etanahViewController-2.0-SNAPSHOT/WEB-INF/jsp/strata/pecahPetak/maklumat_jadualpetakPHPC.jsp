<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {

        $('#menara_bgnn').hide();
       

    });

    function changebgnn(value){

        if(value == "Y")
        {
            $('#menara_bgnn').show();

        }
        if(value == "N")
        {
            $('#menara_bgnn').hide();
        }
    }

    function validateNumber(elmnt,content) {
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
      
    function popup(b,p,idT){

        window.open("${pageContext.request.contextPath}/strata/urusan_pbbm?showForm2&namaBangunan="+b+"&tingkat="+p+"&idTingkat="+idT, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=250");
    }

    function popupPetakAksesori(b,t,p){

        window.open("${pageContext.request.contextPath}/strata/urusan_pbbm?showForm3&idBangunan="+b+"&idTingkat="+t+"&idPetak="+p, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=200");
    }

    <%--function removeBngn(val){

        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/urusan_pbbm?deleteBngn&idBangunan='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

    }

    function removePetak(val){
        
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/urusan_pbbm?deletePetak&idPetak='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
      
    }



    function removeTingkat(val){

        
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/urusan_pbbm?deleteTingkat&idTingkat='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    --%>   function removePtkAksr(val){

        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/urusan_pbbm?deletePtkAksr&idAksesori='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

    }


    function save(event, f){
     
<%--        var nama = document.form1.nama.value;
        var bil_tgkt = document.form1.bil_tgkt.value;
          
        if ((nama == ""))
        {
            alert('Sila masukkan Nama Bangunan ');
            document.form1.nama.focus();
        }
        else if ((bil_tgkt == ""))
        {
            alert('Sila masukkan bilangan tingkat ');
            document.form1.bil_tgkt.focus();
        }
       
        else
        {--%>
        var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');
        }
    <%--}--%>



</script>

<s:form  name="form1" beanclass="etanah.view.strata.MaintainBangunanActionBean">
    <s:messages/>
    <s:errors/>
    <br>
    <div class="subtitle">

        <fieldset class="aras1">
            <a href="#bottom">
                <c:if test="${actionBean.tambah}">
                    <s:button name="Tambah" value="Tambah Bangunan" class="longbtn" /></a>
                </c:if>
                <c:if test="${actionBean.xml}">
                    <s:button name="extrakBangunan" value="Ekstrak Petak" class="longbtn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                </c:if>
        </fieldset>

    </div>
    <br>

    <c:if test="${fn:length(actionBean.pBangunanL) > 0}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Senarai Jadual Petak-petak Bagi Bangunan</legend>
                <br>
                <p>
                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bangunan</th>

                        <th>Senarai Tingkat</th>
                        <c:if test="${fn:length(actionBean.petakL) > 0}">
                            <th>Senarai Petak</th>
                            <th>Keluasan m2</th>
                            <th>Unit-unit Syer</th>
                            <th>Jenis Kegunaan</th>
                            <%--<th</th>--%>
                            <th></th>


                            <th>Petak Aksesori</th>
                            <th>Jenis Kegunaan</th>
                            <th>Lokasi Petak Aksesori</th>
                            <%-- <th></th>--%>
                        </c:if>



                    </tr>
                    <c:set var="items" value="0"/>
                    <c:set var="items2" value="0"/>
                    <c:set var="items3" value="0"/>



                    <c:forEach items="${actionBean.pBangunanL}" var="bgn" varStatus="statusB">

                        <tr>
                            <td>${bgn.nama} 
                                <%-- <c:if test="${statusB.last}">
                                     <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                          onclick="removeBngn('${bgn.idBangunan}')" onmouseover="this.style.cursor='pointer';" >
                                 </c:if>--%>
                            </td>

                            <c:forEach items="${bgn.senaraiTingkat}" var="tgkt" varStatus="status">

                                <td>${tgkt.tingkat}</td>

                                <c:if test="${fn:length(tgkt.senaraiPetak) > 0}">

                                    <c:forEach items="${tgkt.senaraiPetak}" var="petak" varStatus="statusP">

                                        <td>${petak.nama}</td>
                                        <td><s:text name="petakLuas[${items}]"  size="10"/></td>
                                        <td><s:text name="petakSyer[${items}]" size="12"/></td>
                                        <td><s:select name="petakKegunaan[${items}]" style="width:100px">
                                                <s:option value="0">Sila Pilih</s:option>
                                                <s:options-collection collection="${actionBean.kGunaPetakL}" label="nama" value="kod" />
                                            </s:select>
                                            <%--  <td>
                                                  <c:if test="${status.last}">
                                                      <c:if test="${statusP.last}">
                                                          <div align="center">
                                                              <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus ' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                   onclick="removePetak('${petak.idPetak}')" onmouseover="this.style.cursor='pointer';" >
                                                          </div>
                                                      </c:if>
                                                  </c:if>
                                              </td>--%>

                                        <td>
                                            <div align="center">
                                                <img  title="Klik Untuk Tambah Petak Aksesori" alt='Klik Untuk Tambah Petak Aksesori' border='0' src='${pageContext.request.contextPath}/images/tambah.png' class='rem'
                                                      onclick="popupPetakAksesori('${bgn.idBangunan}','${tgkt.idTingkat}','${petak.idPetak}');" onmouseover="this.style.cursor='pointer';" >
                                            </div>
                                        </td>

                                        <c:forEach items="${petak.senaraiPetakAksesori}" var="petakAksesori" varStatus="statusPA">
                                            <td>${petakAksesori.nama}</td>
                                            <td><s:select name="petakKegunaanAksr[${items3}]">
                                                    <s:option value="0">Sila Pilih</s:option>
                                                    <s:options-collection collection="${actionBean.kGunaPetakAksesoriL}" label="nama" value="kod" />
                                                </s:select>
                                            </td>
                                            <td><s:select name="lokasiAksr[${items3}]">
                                                    <s:option value="0">Sila Pilih</s:option>
                                                    <s:option value="Luar Bangunan">Luar Bangunan</s:option>
                                                    <s:option value="Dalam Bangunan">Dalam Bangunan</s:option>
                                                </s:select>

                                                <%--    <td>
                                                        <c:if test="${status.last}">
                                                            <c:if test="${statusP.last}">
                                                                <c:if test="${statusPA.last}">
                                                                    <div align="center">
                                                                        <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                                             onclick="removePtkAksr('${petakAksesori.idAksesori}')" onmouseover="this.style.cursor='pointer';" >
                                                                    </div>
                                                                </c:if>
                                                            </c:if>
                                                        </c:if>
                                                    </td>--%>
                                        <tr>
                                            <c:if test="${not statusPA.last}">
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <%-- <td>&nbsp;</td>--%>

                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </c:if>

                                            <c:if test="${statusPA.last}">
                                            </tr>

                                        </c:if>
                                        <c:set var="items3" value="${items3+1}"/>
                                    </c:forEach>


                                    </tr>

                                    <c:if test="${not statusP.last}">

                                        <td>&nbsp;</td>
                                        <td> <c:if test="${statusP.count eq 1}">
                                                <s:checkbox name="tingkatMezanin[${items2}]" value="${bgn.tingkatMezanin.idTingkat}"></s:checkbox> Mezanin
                                            </c:if>
                                        </td>

                                    </c:if>

                                    <c:if test="${ statusP.last}">

                                        <td>&nbsp;</td>
                                        <td><s:button  onclick="popup('${bgn.nama}','${tgkt.tingkat}','${tgkt.idTingkat}');" value="Tambah Petak" name="tambahPetak" class="btn" /></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <%-- <td>&nbsp;</td>--%>

                                        <%--<c:if test="${fn:length(actionBean.petakAksesoriL) > 0}">--%>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td

                                        <%--</c:if>--%>

                                    </c:if>

                                    <c:if test="${statusP.last}">
                                        <td>&nbsp;</td>
                                        <td> <c:if test="${statusPA.count eq 1}">
                                                <s:checkbox name="tingkatMezanin[${items2}]" value="${bgn.tingkatMezanin.idTingkat}"></s:checkbox> Mezanin
                                            </c:if>
                                        </td>
                                    </c:if>
                                    <c:set var="items" value="${items+1}"/>
                                </c:forEach>
                            </c:if>

                            <c:if test="${fn:length(tgkt.senaraiPetak) == 0}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <c:if test="false">
                                    <td> <s:button  onclick="popup('${bgn.nama}','${tgkt.tingkat}','${tgkt.idTingkat}');" value="Tambah Petak" name="tambahPetak" class="btn" /></td>
                                   </c:if> <%-- <td>
                                         <c:if test="${status.last}">
                                             <div align="center">
                                                 <img title="Klik Untuk Hapus" alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                      onclick="removeTingkat('${tgkt.idTingkat}')" onmouseover="this.style.cursor='pointer';" >
                                             </div>
                                         </c:if>
                                     </td>--%>
                                </c:if>
                            </tr>
                            <c:if test="${not status.last}">
                                <tr>
                                    <td>&nbsp;</td>
                                </c:if>

                                <c:set var="items2" value="${items2+1}"/>

                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
                </p>
                <br>
                <label>&nbsp;</label>
                <s:button name="simpanSemua" value="Kemaskini" class="btn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                <br>
                <br>
            </fieldset>

        </div>
    </c:if>

    <br>

    <div class="subtitle">

        <fieldset class="aras1">
            <a name="bottom">
                <legend>Tambah Bangunan</legend>
                <p>
                    <label>Bangunan (B)M:</label><s:text name="bangunan.nama" id="nama" onkeyup="validateNumber(this,this.value);" /><em>*</em>
                </p>
                <p>
                    <label>Nama Lain :</label><s:text name="bangunan.namaLain" />
                </p>
                <p>
                    <label>Jumlah Tingkat :</label><s:text name="bangunan.bilanganTingkat" id="bil_tgkt" onkeyup="validateNumber(this,this.value);"/><em>*</em>
                </p>
                <p>
                    <label>Kegunaan :</label><s:checkbox name="bangunan.untukKediaman" value="Y" /> Kediaman
                </p>
                <p>
                    <label>&nbsp;</label><s:checkbox name="bangunan.untukPejabat" value="Y" /> Pejabat
                </p>
                <p>
                    <label>&nbsp;</label><s:checkbox name="bangunan.untukPerniagaan" value="Y" /> Perniagaan
                </p>
                <p>
                    <label>(Jika lain kegunaan)</label><s:text name="bangunan.untukKegunaanLain" />
                </p>
                <p>
                    <label>Jenis Bangunan :</label><s:radio name="bangunan.kekal" value="Y"></s:radio> Kekal
                    <s:radio name="bangunan.kekal" value="N"></s:radio> Sementara
                </p>
                <p>
                    <label>*Menara :</label><s:radio name="menara" value="Y" onchange="javaScript:changebgnn(this.value)"></s:radio> Ya
                    <s:radio name="menara" value="N" onchange="javaScript:changebgnn(this.value)"></s:radio> Tidak <em>*</em>
                </p>
                <div id="menara_bgnn" class="subtitle">
                    <fieldset class="aras1">
                        <legend>Menara Bangunan</legend>
                        <p>
                            <label>Bilangan Menara Bagi Bangunan :</label><s:text name="bangunan.bilanganMenara" ></s:text> <em>*</em>
                        </p>
                    </fieldset>
                </div>
                <br>
                <label>&nbsp;</label>
                <s:button name="tambahBangunan" value="Simpan" class="btn" onclick="save(this.name,this.form);"/>
                <%--<s:button name="tambahBangunan" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>--%>
                <br>
                <br>
                </fieldset>
                </div>
                <br>
            </s:form>