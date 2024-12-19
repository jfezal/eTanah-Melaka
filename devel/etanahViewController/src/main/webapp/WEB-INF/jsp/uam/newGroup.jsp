<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<style type="text/css">
    label{

    }
</style>
<script type="text/javascript">
    $(document).ready(function() { 
        //alert($('input[name="chb"]').val());
        showSelectedCheckbox();
    });
    
    function showSelectedCheckbox(){
      
        var val = [];
        $(':checkbox:checked').each(function(i){
            val[i] = $(this).val();
        });

        var par ="";
        for(var k in val){
            par = par + $('#chb'+ val[k]).next().text();
            if(k != val.length - 1){
                par = par + ",";
            }
        }
        
        //        for(var k = 0 ; k < val.length; k++){
        //            par = par + $('#'+ val[k]).next().text();
        //            
        //            if(val[k] == val.length - 1){
        //                par = par + ",";
        //            }
        //        }
        
        //alert(par);
        //alert(param);
        if($(':checkbox:checked')){
            //alert(id + ' checked');
            //            $.post('${pageContext.request.contextPath}/uam/new_group?showSelectedPeranan'+param,
            //            function(data){
            //                if(data != ''){
            //                    //alert(data);
            //                    $('#perananTambahan').html(data);                        
            //                }else{
            //                     $('#perananTambahan').html("");
            //                }
            //            }, 'html');
            //         var par = $(':checkbox:checked').next().text() ;
            //            par.split("");
            //            alert(par[0]);
            //alert(par);
            if(par != ''){
                $('#perananTambahan').html(par); 
            }else{
                $('#perananTambahan').html("");
            }
            
        }
        
        
       
    }
</script>
<s:form beanclass="etanah.view.uam.NewGroup">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Peranan Pengguna</legend>
            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                    <s:text name="idPengguna"/>


            </p>
            <!--            <p>
                            <label><font color="red">*</font>Jabatan : </label>
            <s:select name="kodJabatan" id="kodJabatan">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${list.senaraiKodJabatan}" label="nama" value="kod"/>
            </s:select>

        </p>-->
            <p>
                <label><font color="red">*</font>Unit : </label>
                    <s:select name="kodUnit" id="kodUnit">
                        <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodUnit}" label="nama" value="id"/>
                </s:select>

            </p>

            <p><label>&nbsp;</label>
                <s:submit name="searchPeranan" id="search" value="Cari" class="btn"/>
            </p>

            <c:if test="${fn:length(actionBean.listPerananB) > 0}">


                <p>
                    <label>Peranan Utama : </label>
                    ${actionBean.pguna.perananUtama.nama}
                    <s:hidden name="kodPeranan" value="${actionBean.pguna.perananUtama.kod}" id="kod"/>
                    &nbsp;
                </p>


                <%--
                <p>
                    <label>Peranan Utama :</label>

                    <s:select name="kodPeranan" style="width:250px">
                        <s:option value="" >Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodPeranan}" value="kod" label="nama" id="peranan"/>
                    </s:select>
                </p>
                --%>



                <p>
                    <label>Peranan Tambahan :</label>


                    <display:table class="" name= "${actionBean.listPerananTambahan}" id="lpt" >
                        <display:column style="background:inherit;">${lpt_rowNum})</display:column>
                        <display:column style="background:inherit;">${lpt.peranan.nama}</display:column>
                        <display:column></display:column>
                    </display:table>

                    &nbsp;
                </p>
                <p>
                    <label>Anda memilih : </label>
                    &nbsp;<span id="perananTambahan"></span>
                </p>
            </c:if>

            <center>
                <table border=0 class="tablecloth"><tr>
                        <c:forEach var="peranan"  varStatus="line" items="${actionBean.listPerananB}">
                            <th align=right>
                                ${line.count} :
                            </th>
                            <td align=left>
                                <c:set var="checked" value=""/>
                                <c:forEach var="s" items="${actionBean.listPerananValue}">

                                    <c:if test="${s.peranan.kod eq peranan.kod}">
                                        <c:set var="checked" value="checked"/>
                                    </c:if>
                                </c:forEach>
                                <%--<s:checkbox checked="checked" name="listPeranan[${i - 1}].nama"/>${peranan.nama}&nbsp;--%>
                                <input type="checkbox" id="chb${peranan.kod}" name="chb" ${checked} value="${peranan.kod}" onClick="showSelectedCheckbox('${peranan.kod}');" />
                                <label for="chb${peranan.kod}" style="width:auto;text-align: left;margin-left: 0px;"> ${peranan.nama}&nbsp;</label>
                            </td>
                            <c:if test="${line.count % 3 == 0}" >
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </center>
        </fieldset>
        <c:if test="${simpan}">
            <p>
                <label>&nbsp;</label>
                <s:submit name="save" id="save" value="Simpan" class="btn"/>
                <%--<s:submit name="delete" id="delete" value="Batal Semua" class="btn" onclick="return confirm('Anda Pasti Untuk Batalkan Semua Peranan Tambahan ?')"/>--%>
            </p>
        </c:if>
    </div>
</s:form>
