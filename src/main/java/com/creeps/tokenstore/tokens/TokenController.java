package com.creeps.tokenstore.tokens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/tokens/")
@RestController
public class TokenController {

    @Autowired
    private TokenRepo repo;
    @GetMapping("info/{token}/")
    public ResponseEntity<Token> getTokenInfo(@PathVariable("token") String token){
        System.out.println(token);
        Token t = repo.findByToken(token);
        if(t!=null)
            return ResponseEntity.status(200).body(t);
        return ResponseEntity.status(404).build();

    }

    @PostMapping("create/")
    public ResponseEntity<Token> createToken(@Valid @RequestBody Token token){
        token.setToken();
        System.out.println(token.getToken());
        return ResponseEntity.ok(this.repo.save(token));
    }






    @PutMapping("update/{id}")
    public ResponseEntity<Token> updateToken(@PathVariable("id") Long id,@Valid @RequestBody Token updated){
        Token token = this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("token", "id", id));
        token.setPermissions(updated.getPermissions());
        token.setToken();
        this.repo.save(token);
        return ResponseEntity.status(200).body(token);
    }

}
